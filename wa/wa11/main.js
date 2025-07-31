const displayedImage = document.querySelector('.displayed-img');
const thumbBar = document.querySelector('.thumb-bar');

const btn = document.querySelector('button');
const overlay = document.querySelector('.overlay');

/* Declaring the array of image filenames */
//const imageFiles = ["images/pic1.jpg","images/pic2.jpg","images/pic3.jpg","images/pic4.jpg","images/pic5.jpg"];
/*DON'T NEED IT*/

/* Declaring the alternative text for each image file */
//const altTexts = ["Closeup of a human eye","a rock or wave or something","purple and white flowers","ancient egyptian wall paintings","Brown butterfly. A brownterfly, if you will."];
/*ALSO DON'T NEED IT*/

/* Looping through images */
for (let i=0;i<5;i++){
    const newElement = document.createElement("img");
    const imageFile = "images/"+String(i+1)+".bmp";
    const altText = "A beautiful rendition of the number "+String(i+1)+".";
    newElement.setAttribute("src",imageFile);//instead of imageFiles[i]
    newElement.setAttribute("alt",altText);//instead of altTexts[i]
    newElement.addEventListener("click",()=>{
        displayedImage.setAttribute("src",imageFile);
        displayedImage.setAttribute("alt",altText);
    });
    thumbBar.appendChild(newElement);
}

/* Wiring up the Darken/Lighten button */
btn.addEventListener("click",()=>{
    const darkOrLight = btn.getAttribute("class");
    if (darkOrLight==="dark"){
        btn.setAttribute("class", "light");
        btn.textContent = "Lighten";
        overlay.style.backgroundColor = "rgb(0 0 0 / 50%)";
    }
    else{
        btn.setAttribute("class", "dark");
        btn.textContent = "Darken";
        overlay.style.backgroundColor = "rgb(0 0 0 / 0%)";
    }
});