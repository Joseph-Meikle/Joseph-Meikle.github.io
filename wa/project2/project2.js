var digitBoxes=[...document.querySelectorAll(".guessRow span")];//all 10 digit boxes
const submitButton=document.getElementById("resultsButton");
const allGuesses=document.getElementById("guesses");
const topRow=document.querySelector(".guessRow");

//guess generation logic
var green=[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1];//not -1 if place is known
var minCount=[0,0,0,0,0,0,0,0,0,0];//min number of that digit (not counting green)
var maxCount=[10,10,10,10,10,10,10,10,10,10];//minCount except max
var digOptions=Array.from({length:10},()=>[0,1,2,3,4,5,6,7,8,9]);

function debugPrint(){//wouldn't you know it, it's for debugging
    console.log(green);
    console.log(digOptions);
    console.log(minCount);
    console.log(maxCount)
}

function newGuessRow(){
    const copyRow = topRow.cloneNode(true);//deep copy
    topRow.opacity=0;
    for (let i=0;i<10;i++){
        const child=copyRow.children[i];
        child.style.borderStyle="solid";
    }
    allGuesses.insertBefore(copyRow,allGuesses.children[2]);
    for (let i=2;i<allGuesses.children.length;i++){
        const tempGuessRow=allGuesses.children[i];
        tempGuessRow.style.opacity=0.6**(i-1);
        tempGuessRow.style.animation="";
        void tempGuessRow.offsetHeight;//force reflow
        tempGuessRow.style.animation="drop-row 1s cubic-bezier(0.375, 0.82, 0.165, 1) 1";
    }
}
function endGame(valid,newRow=true){
    if (valid===true){
        console.log("Phone number submitted!!");//
        if (newRow) newGuessRow();
        topRow.opacity=0;
        submitButton.removeEventListener("click",readResults);//turn off submit button FOREVER
        submitButton.style.backgroundColor="gray";
        submitButton.style.color="lightgray";
        //submitButton.id="yayButton";//
        //submitButton.value="SUCCESS";//
        guess = ["S","U","B","M","I","T","T","E","D","!"];
        for (let i=0;i<10;i++){
            const dB=digitBoxes[i];
            dB.textContent=String(guess[i]);
            dB.className="success";
        }
        topRow.style.animation="";
        void topRow.offsetHeight;//force reflow
        topRow.style.animation="fade-in 1.1s cubic-bezier(0.375, 0.82, 0.765, 1) 1";
    }
    else{
        console.log("LIAR!");//
        if (newRow) newGuessRow();
        topRow.opacity=0;
        submitButton.removeEventListener("click",readResults);//turn off submit button FOREVER
        submitButton.style.backgroundColor="gray";
        submitButton.style.color="lightgray";
        guess = [" ","Y","O","U"," ","L","I","A","R"," "];
        for (let i=0;i<10;i++){
            const dB=digitBoxes[i];
            dB.textContent=String(guess[i]);
            dB.className="invalid";
        }
        topRow.style.animation="";
        void topRow.offsetHeight;//force reflow
        topRow.style.animation="fade-in 1.1s cubic-bezier(0.375, 0.82, 0.765, 1) 1";
    }
    //hide or remove submit button (aka end the game)
}

function generateGuessHelper(i, guess, yellowCount, guessCount, indOrder){
    if (yellowCount>indOrder.length-i) return false;
    if (i>=indOrder.length) return true;
    let ind=indOrder[i];
    for (let dig of digOptions[ind]){
        if (guessCount[dig]<maxCount[dig]){
            let yellowOption = (guessCount[dig]<minCount[dig]);//if contributes towards minCount
            guess[ind]=dig;
            guessCount[dig]++;
            if (generateGuessHelper(i+1,guess,yellowCount-(yellowOption?1:0),guessCount,indOrder)){
                return true;
            }
            guessCount[dig]--;
        }
    }
    return false;
}
function generateGuess(){
    //initialize
    let yellowCount=0;
    let indOrder=[];
    for (let i=0;i<10;i++){//a few things
        digOptions[i].sort(() => Math.random()-0.5);//sort by... random! (shuffle)
        yellowCount+=minCount[i];
        if (green[i]===-1){
            indOrder.push(i);
        }
    }
    var guess=[...green];
    indOrder.sort(() => Math.random()-0.5);//shuffle order of indices to fill!
    //try everything, in a random order
    foundGuess=generateGuessHelper(0,guess,yellowCount,[0,0,0,0,0,0,0,0,0,0],indOrder);
    if (foundGuess===false){//if no valid guess found
        console.log("No possible phone number given the constraints");
        endGame(false,false);
        return;
    }
    //put into the html blocks
    for (let i=0;i<10;i++){
        const dB=digitBoxes[i];
        dB.textContent=String(guess[i]);
        dB.className="whiteBox";
    }
    topRow.style.animation="";
    void topRow.offsetHeight;//force reflow
    topRow.style.animation="fade-in 1.1s cubic-bezier(0.375, 0.82, 0.765, 1) 1";
    submitButton.addEventListener("click",readResults);
}
function readResults(){//aka update constraints
    submitButton.removeEventListener("click",readResults);//turn off submit button until next guess is ready
    //add loading symbol/animation if necessary
    let digCount=[0,0,0,0,0,0,0,0,0,0];
    
    for (let i=0;i<10;i++){//making ungraded boxes default to gray
        const dB=digitBoxes[i];
        const boxClass = dB.className;
        if (boxClass==="whiteBox"){
            dB.className="grayBox";    
        }    
    }
    for (let i=0;i<10;i++){//greens
        const dB=digitBoxes[i];
        const dig=parseInt(dB.textContent);
        const boxClass = dB.className;
        if (green[i]!==-1){//expecting green
            if (boxClass!=="greenBox"){//LIAR!
                console.log("Same digit on ind="+i+" no longer green");
                endGame(false);
                return;
            }
            continue;
        }
        else if (boxClass==="greenBox"){//new green!
            if (minCount[dig]>0) minCount[dig]--;
            maxCount[dig]--;
            if (maxCount[dig]===0){//if no more of dig
                for (let j=0;j<10;j++){//making things a little nicer for the guess generator
                    digOptions[j]=digOptions[j].filter((x) => x!==dig);
                }
            }
            digOptions[i]=[];//for clarity
            green[i]=dig;
        }
    }
    for (let i=0;i<10;i++){//yellows and grays
        const dB=digitBoxes[i];
        const dig=parseInt(dB.textContent);
        const boxClass = dB.className;
        if (boxClass==="yellowBox"){
            digCount[dig]++;
            digOptions[i]=digOptions[i].filter((x) => x!==dig);
        }
        else if (boxClass==="grayBox"){
            if (digCount[dig]<minCount[dig] || digCount[dig]>maxCount[dig]){//LIAR
                console.log("Contradicts min and/or max for "+dig+" determined earlier");
                endGame(false);
                return;
            }
            if (digCount[dig]===0 && maxCount[dig]>0){
                for (let j=0;j<10;j++){//making things a little nicer for the guess generator
                    digOptions[j]=digOptions[j].filter((x) => x!==dig);
                }
            }
            else{
                digOptions[i]=digOptions[i].filter((x) => x!==dig);
            }
            minCount[dig]=digCount[dig];
            maxCount[dig]=digCount[dig];//aka, exact count known
        }
    }
    for (let dig=0;dig<10;dig++){//check counts
        if (digCount[dig]>maxCount[dig]){//should only be if user yellowed box after graying box with same dig
            console.log("Gray before yellow for digit "+dig);
            endGame(false);
            return;
        }
        if (digCount[dig]>minCount[dig]){
            minCount[dig]=digCount[dig];
        }
    }
    if (!green.includes(-1)){
        endGame(true);//win
        return;
    }
    newGuessRow();
    //do a little animation thing to push old guess down?
    generateGuess();
}

//initialization/startup
for (let i=0;i<digitBoxes.length;i++){
    const dB=digitBoxes[i];
    dB.addEventListener("click",()=>{
        const boxMode = dB.className;
        if (boxMode==="whiteBox" || boxMode==="grayBox"){
            dB.className="greenBox";
        }
        else if (boxMode==="greenBox"){
            dB.className="yellowBox";
        }
        else if (boxMode==="yellowBox"){
            dB.className="grayBox";
        }
    });
}
generateGuess();