package questions;

public class LiteTextEditor {
    public static void main(String[] args) {

        TextBlock h = new TextBlock("Hello World!");
        TextBlock j = new TextBlock("Java Programming");
        TextBlock e = new TextBlock();

        h.display();
        j.display();
        e.display();
        System.out.println();

        Editor ed = new Editor("Initial Text. ");

        System.out.println("After Append:");
        ed.appendText("This is a live edit.");
        System.out.println(ed.current());
        System.out.println();

        System.out.println("After Delete:");
        ed.deleteText(0, 7); // delete only "Initial", keep space
        System.out.println(ed.current());
        System.out.println();

        System.out.println("After Replace:");
        int idx = ed.search("live");
        ed.replaceText(idx, idx + 4, "real-time");
        System.out.println(ed.current());
        System.out.println();

        System.out.println("Substring (0-12):");
        System.out.println(ed.substring(0, 12));
        System.out.println();

        System.out.println("Index of 'edit':");
        System.out.println(ed.search("edit"));
        System.out.println();

        System.out.println("Validation Check:");
        System.out.print(
            "Does live text match 'Java Programming'? " +
            ed.validate(j.getContent())
        );
    }
}

class TextBlock {
    private final String content;

    TextBlock() {
        this.content = "";
    }

    TextBlock(String content) {
        this.content = content;
    }

    String getContent() {
        return content;
    }

    void display() {
        System.out.println(content);
    }
}

class Editor {
    private StringBuilder liveText;

    Editor() {
        this.liveText = new StringBuilder();
    }

    Editor(String initial) {
        this.liveText = new StringBuilder(initial);
    }

    void appendText(String text) {
        liveText.append(text);
    }

    void deleteText(int start, int end) {
        liveText.delete(start, end);
    }

    void replaceText(int start, int end, String replacement) {
        liveText.replace(start, end, replacement);
    }

    String substring(int start, int end) {
        return liveText.substring(start, end);
    }

    int search(String word) {
        return liveText.indexOf(word);
    }

    boolean validate(String input) {
        return liveText.toString().equals(input);
    }

    String current() {
        return liveText.toString();
    }
}
