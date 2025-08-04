const digitBoxes=[...document.querySelectorAll("#current span")];//all 10 digit boxes

for (let i=0;i<digitBoxes.length;i++){
    const dB=digitBoxes[i];
    dB.addEventListener("click",()=>{
        const boxMode = dB.getAttribute("class");
        if (boxMode==="whiteBox" || boxMode==="grayBox"){
            dB.setAttribute("class","greenBox");
        }
        else if (boxMode==="greenBox"){
            dB.setAttribute("class","yellowBox");
        }
        else if (boxMode==="yellowBox"){
            dB.setAttribute("class","grayBox");
        }
    });
}

var green=["","","","","","","","","",""];//non-empty if that place is known
var minCount=[0,0,0,0,0,0,0,0,0,0];//min number of that digit (not counting green)
var maxCount=[10,10,10,10,10,10,10,10,10,10];//minCount except max
var notHere=[[],[],[],[],[],[],[],[],[],[]];//where digits were previously yellow 

var guess=["","","","","","","","","",""];//will fill whenever making a guess
var guessCount=[0,0,0,0,0,0,0,0,0,0];//will fill whenever making a guess
function makeGuess(){
    toShuffle=[];
    let digitCount=10;//how many digits to randomly add
    let randomOptions=[];
    let miniNotHere=[];//just the columns for non-green digits
    for (let i=0;i<10;i++){//doing a few different setup things
        if (green[i]!="") digitCount--;
        else miniNotHere.push(notHere[i]);//add relevant column from notHere
        digitCount-=minCount[i];
        if (maxCount[i]>minCount[i]) randomOptions.push(i);
        toShuffle.push(Array(minCount[i]).fill(i));//add the digit the min number of times
    }
    toShuffle.push(Array(digitCount).fill(-1));//placeholder numbers
    console.log(randomOptions);
    console.log("digitCount: "+digitCount);
    guessCount=minCount;//how many of each digit in guess
    while (digitCount>0){
        const ind = Math.floor(Math.random()*randomOptions.length);
        const digit = randomOptions[ind];
        guessCount[digit]++;
        toShuffle.push(digit);
        if (guessCount[digit]==maxCount[digit]){
            randomOptions.splice(ind,1);
        }
        digitCount--;
    }
    toShuffle.sort(() => Math.random() - 0.5);//sort by... random! (shuffle)
    //make sure it's not retrying any yellow digits in the same place
    for (let i=0;i<10;i++){
        let swapInd=i+1;
        let valid=false;
        while (valid===false){
            valid=true;
            notHere[i].forEach((digit) =>{
                if (toShuffle[i]===digit){
                    valid=false;
                    swapInd
                }
            });
        }
    }
    guess=green;
    for (let i=0;i<10;i++){
        if (guess[i]===""){
            guess[i]=toShuffle.pop();
        }
    }

    //put into the html blocks
    for (let i=0;i<10;i++){
        const dB=digitBoxes[i];
        dB.textContent=String(guess[i]);
    }
}
function readResults(){

}

makeGuess();