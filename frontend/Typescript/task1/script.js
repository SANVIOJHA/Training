// const button = document.getElementById("loadBtn");
// const container = document.getElementById("imageContainer");

// button.addEventListener("click", fetchImages);

// async function fetchImages() {
//     try {
        
//         let response = await fetch("https://picsum.photos/v2/list?page=1&limit=6");

//         let data = await response.json();

//         container.innerHTML = ""; 

//         data.forEach(img => {
//             let image = document.createElement("img");
//             image.src = img.download_url;
//             container.appendChild(image);
//         });

//     } catch (error) {
//         console.log("Error fetching images:", error);
//     }
// }



const button = document.getElementById("loadBtn");
const container = document.getElementById("imageContainer");

button.addEventListener("click", fetchImages);

async function fetchImages() {
    try {
        // random page number (1–70)
        let randomPage = Math.floor(Math.random() *50) + 1;

        let response = await fetch(`https://picsum.photos/v2/list?page=${randomPage}&limit=6`);
        let data = await response.json();

        container.innerHTML = "";

        data.forEach(img => {
            let image = document.createElement("img");
            image.src = img.download_url;
            container.appendChild(image);
        });

    } catch (error) {
        console.log("Error fetching images:", error);
    }
}