
var customName = document.getElementById('customname');
var startButton = document.querySelector(".randomize");
var storyPlace = document.querySelector(".story");
const storyText = "“Wow, that’s a lot of ink!” the cashier said as he scanned the gallon jug. “Are you bulk-buying or is this all for one project?”\n“… I guess you could say all one project,” Nate replied, trying to hide a smile.\n\nNate sat in his car, parked outside the post office, trying to figure out how to best hide the enormous ink bottle on his person. It was too large to hide under his clothes, so he ultimately just stuck it in a Trader Joe’s shopping bag and called it good enough. He wouldn’t need to hide it for very long, anyways.\nHe entered the post office, doing his best nonchalant walk (which he had practiced in front of the mirror the night before). It was fairly crowded, so he had a hard time identifying the employee he was looking for, but at last, he spotted them… Whitney. He quickly got in their line, and 45 minutes later he made it to the front.\n“Hi, how can I help…” Whitney’s voice suddenly faltered as they saw their current customer. “Nate… don’t do it.”\n“INK to meet you!” Nate quickly drew the gallon of ink from his bag, removed the lid, and flipped it upside-down over the counter. It only took a couple seconds for every paper, envelope, and computer keyboard in the vicinity to be destroyed. The extra two and a half minutes it took to empty the rest of the bottle were just for emphasis. \nWhitney, practically smothered in ink, exclaimed, “Nate, more like HATE!” as they shook their fist.\nEveryone gasped. This was a very hurtful comment.";
var ukButton = document.getElementById("uk");
var extraElements = [];

startButton.addEventListener("click",() => {
  console.log("generating story...");//
  
  let customizedText = storyText;
  if (customName.value!=""){
    customizedText=customizedText.replaceAll("Whitney",customName.value);
  }
  if (ukButton.checked){
    customizedText=customizedText.replaceAll("gallon","3.78541 liter");
    customizedText=customizedText.replaceAll("minutes","British Minutes");
    customizedText=customizedText.replaceAll("Trader Joe's","Waitrose");
  }
  let storyLines=customizedText.split("\n");
  storyPlace.textContent=storyLines[0];
  storyPlace.style.visibility = "visible";
  if (extraElements.length==0){
    for (let i=1;i<storyLines.length; i++){
        const nextLinePlace = storyPlace.cloneNode();
        nextLinePlace.textContent=storyLines[i];
        document.body.appendChild(nextLinePlace);
        extraElements.push(nextLinePlace);
    }
  }
  else{
    for (let i=1;i<storyLines.length; i++){
        extraElements[i-1].textContent=storyLines[i];
    }
  }
});
