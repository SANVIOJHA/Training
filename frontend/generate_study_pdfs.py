from pathlib import Path
import re

from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib.units import inch
from reportlab.platypus import (
    ListFlowable,
    ListItem,
    PageBreak,
    Paragraph,
    Preformatted,
    SimpleDocTemplate,
    Spacer,
)


ROOT = Path(r"D:\CAPGEMENI_JAVA_FULLSTACK\Frontend_part")
SOURCE = ROOT / "JS_TS_React_Study_Guide.md"
FULL_PDF = ROOT / "JS_TS_React_Study_Guide.pdf"
VIVA_PDF = ROOT / "Most_Important_Viva_QA.pdf"


def build_styles():
    styles = getSampleStyleSheet()
    styles.add(
        ParagraphStyle(
            name="GuideTitle",
            parent=styles["Title"],
            fontName="Helvetica-Bold",
            fontSize=22,
            leading=28,
            alignment=TA_CENTER,
            spaceAfter=18,
            textColor=colors.HexColor("#0f172a"),
        )
    )
    styles.add(
        ParagraphStyle(
            name="GuideH1",
            parent=styles["Heading1"],
            fontName="Helvetica-Bold",
            fontSize=18,
            leading=22,
            spaceBefore=12,
            spaceAfter=10,
            textColor=colors.HexColor("#1d4ed8"),
        )
    )
    styles.add(
        ParagraphStyle(
            name="GuideH2",
            parent=styles["Heading2"],
            fontName="Helvetica-Bold",
            fontSize=15,
            leading=19,
            spaceBefore=10,
            spaceAfter=8,
            textColor=colors.HexColor("#1e3a8a"),
        )
    )
    styles.add(
        ParagraphStyle(
            name="GuideH3",
            parent=styles["Heading3"],
            fontName="Helvetica-Bold",
            fontSize=12,
            leading=16,
            spaceBefore=8,
            spaceAfter=6,
            textColor=colors.HexColor("#334155"),
        )
    )
    styles.add(
        ParagraphStyle(
            name="GuideBody",
            parent=styles["BodyText"],
            fontName="Helvetica",
            fontSize=10.5,
            leading=15,
            spaceAfter=6,
        )
    )
    styles.add(
        ParagraphStyle(
            name="GuideCode",
            parent=styles["Code"],
            fontName="Courier",
            fontSize=8.5,
            leading=11,
            leftIndent=18,
            rightIndent=18,
            backColor=colors.HexColor("#f8fafc"),
            borderPadding=8,
            borderWidth=0.5,
            borderColor=colors.HexColor("#cbd5e1"),
            spaceBefore=4,
            spaceAfter=8,
        )
    )
    return styles


def clean_inline(text: str) -> str:
    text = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
    text = re.sub(r"`([^`]+)`", r"<font name='Courier'>\1</font>", text)
    text = re.sub(r"\*\*(.+?)\*\*", r"<b>\1</b>", text)
    return text


def parse_markdown(md_text: str):
    lines = md_text.splitlines()
    blocks = []
    i = 0
    while i < len(lines):
        line = lines[i].rstrip()

        if not line.strip():
            i += 1
            continue

        if line.strip() == "---":
            blocks.append({"type": "separator"})
            i += 1
            continue

        if line.startswith("```"):
            fence = line[:3]
            code_lines = []
            i += 1
            while i < len(lines) and not lines[i].startswith(fence):
                code_lines.append(lines[i])
                i += 1
            i += 1
            blocks.append({"type": "code", "text": "\n".join(code_lines)})
            continue

        if line.startswith("### "):
            blocks.append({"type": "h3", "text": line[4:].strip()})
            i += 1
            continue

        if line.startswith("## "):
            blocks.append({"type": "h2", "text": line[3:].strip()})
            i += 1
            continue

        if line.startswith("# "):
            blocks.append({"type": "h1", "text": line[2:].strip()})
            i += 1
            continue

        if re.match(r"^\d+\.\s+", line):
            items = []
            while i < len(lines) and re.match(r"^\d+\.\s+", lines[i].strip()):
                items.append(re.sub(r"^\d+\.\s+", "", lines[i].strip()))
                i += 1
            blocks.append({"type": "olist", "items": items})
            continue

        if line.startswith("- "):
            items = []
            while i < len(lines) and lines[i].strip().startswith("- "):
                items.append(lines[i].strip()[2:].strip())
                i += 1
            blocks.append({"type": "ulist", "items": items})
            continue

        para = [line.strip()]
        i += 1
        while i < len(lines):
            nxt = lines[i].rstrip()
            if (
                not nxt.strip()
                or nxt.strip() == "---"
                or nxt.startswith("#")
                or nxt.startswith("```")
                or nxt.strip().startswith("- ")
                or re.match(r"^\d+\.\s+", nxt.strip())
            ):
                break
            para.append(nxt.strip())
            i += 1
        blocks.append({"type": "p", "text": " ".join(para)})
    return blocks


def blocks_to_story(blocks, title):
    styles = build_styles()
    story = [Paragraph(clean_inline(title), styles["GuideTitle"]), Spacer(1, 0.12 * inch)]

    for block in blocks:
        kind = block["type"]
        if kind == "separator":
            story.append(Spacer(1, 0.08 * inch))
        elif kind == "h1":
            story.append(Paragraph(clean_inline(block["text"]), styles["GuideH1"]))
        elif kind == "h2":
            story.append(Paragraph(clean_inline(block["text"]), styles["GuideH2"]))
        elif kind == "h3":
            story.append(Paragraph(clean_inline(block["text"]), styles["GuideH3"]))
        elif kind == "p":
            story.append(Paragraph(clean_inline(block["text"]), styles["GuideBody"]))
        elif kind == "code":
            story.append(Preformatted(block["text"], styles["GuideCode"]))
        elif kind == "ulist":
            items = [
                ListItem(Paragraph(clean_inline(item), styles["GuideBody"]))
                for item in block["items"]
            ]
            story.append(ListFlowable(items, bulletType="bullet", leftIndent=18))
            story.append(Spacer(1, 0.06 * inch))
        elif kind == "olist":
            items = [
                ListItem(Paragraph(clean_inline(item), styles["GuideBody"]))
                for item in block["items"]
            ]
            story.append(ListFlowable(items, bulletType="1", leftIndent=18))
            story.append(Spacer(1, 0.06 * inch))
    return story


def build_pdf(output_path: Path, blocks, title: str):
    doc = SimpleDocTemplate(
        str(output_path),
        pagesize=A4,
        rightMargin=44,
        leftMargin=44,
        topMargin=44,
        bottomMargin=44,
        title=title,
        author="OpenAI Codex",
    )
    story = blocks_to_story(blocks, title)
    doc.build(story)


def extract_important_viva(md_text: str) -> str:
    match = re.search(
        r"## 13\. Most Important Viva Questions to Practice First(.*?)## 14\.",
        md_text,
        re.S,
    )
    if not match:
        raise ValueError("Could not find the important viva question list.")

    question_lines = re.findall(r"^\d+\.\s+(.*)$", match.group(1), re.M)
    wanted = []
    for line in question_lines:
        normalized = line.strip()
        if normalized.endswith("?"):
            wanted.append(normalized)
        else:
            wanted.append(normalized)

    qa_matches = re.findall(r"## (Q\d+\..*?)(?=\n## Q\d+\.|\n---|\Z)", md_text, re.S)
    qa_map = {}
    for chunk in qa_matches:
        lines = chunk.strip().splitlines()
        header = lines[0].strip()
        question_text = re.sub(r"^Q\d+\.\s*", "", header)
        qa_map[question_text.strip()] = "\n".join(lines)

    sections = [
        "# Most Important Viva Questions with Answers",
        "",
        "This PDF contains the highest-priority viva questions from the main guide, along with their detailed answers for quick practice.",
        "",
    ]

    for question in wanted:
        if question in qa_map:
            sections.append(f"## {qa_map[question].splitlines()[0]}")
            answer_lines = qa_map[question].splitlines()[1:]
            sections.extend(answer_lines)
            sections.append("")
        else:
            sections.append(f"## {question}")
            sections.append("Answer not found in the source guide.")
            sections.append("")
    return "\n".join(sections)


def main():
    md_text = SOURCE.read_text(encoding="utf-8")
    full_blocks = parse_markdown(md_text)
    build_pdf(FULL_PDF, full_blocks, "JavaScript, TypeScript, and React Study Guide")

    viva_md = extract_important_viva(md_text)
    viva_blocks = parse_markdown(viva_md)
    build_pdf(VIVA_PDF, viva_blocks, "Most Important Viva Questions with Answers")

    print(FULL_PDF)
    print(VIVA_PDF)


if __name__ == "__main__":
    main()
