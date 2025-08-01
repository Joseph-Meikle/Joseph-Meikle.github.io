const endpoint = "https://random.dog/woof.json";
const picPlace = document.querySelector("body article img");
const dogButton = document.querySelector("button");
dogButton.addEventListener("click",getDogPic);
picPlace.setAttribute("height",String(0.6*window.innerHeight)+"px");

async function getDogPic() {
    try {
        const response = await fetch(endpoint);
        if (!response.ok){
            throw Error(response.statusText);
        }
        const dogObj = await response.json();
        const dogUrl = await dogObj.url;
        //console.log(dogUrl);
        const fileEnding = dogUrl.substring(dogUrl.length-4);
        if (fileEnding==".mp4" || fileEnding=="webm"){
            console.log("This \"random dog picture\" API just gave me a VIDEO");
            getDogPic();
            return;
        }
        picPlace.setAttribute("src",dogUrl);
    } catch (err) {
        console.log(err);
        alert("NO DOGS");
    }
}

getDogPic();//call once on page load