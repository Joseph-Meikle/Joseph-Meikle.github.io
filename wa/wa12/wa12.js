const endpoint = "https://placebear.com/900/600";
const picPlace = document.querySelector("body article img");
const dogButton = document.querySelector("button");
dogButton.addEventListener("click",getFact);

async function getFact() {
    try {
        const response = await fetch(endpoint);
        if (!response.ok){
            throw Error(response.statusText);
        }
        const bearSrc = await response.text();
        picPlace.setAttribute("src",bearSrc);
    } catch (err) {
        console.log(err);
        alert("Bearless...");
    }
}

getFact();//call once on page load