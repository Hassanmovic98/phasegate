console.log("Welcome to semi colon Africa StudentGrade System");

const prompt = require('prompt-sync')();

let numofStudents = parseInt(prompt("what are the numbers of students: "));
let numofSubjects = parseInt(prompt("what are the numbers of subjects offerred: "));

let scores = [];
let studentTotals = new Array(numofStudents).fill(0);
let studentAverages = new Array(numofStudents).fill(0);

for (let studentIndex = 0; studentIndex < numofStudents; studentIndex++) {
    console.log("collecting scores of students " + (studentIndex + 1));
    let totalScore = 0;
    scores[studentIndex] = [];

    for (let subjectIndex = 0; subjectIndex < numofSubjects; subjectIndex++) {
        let score = -1;

        while (score < 0 || score > 100) {
            score = parseInt(prompt("Please enter the score for Subjects " + (subjectIndex + 1) + ": "));
            if (score < 0 || score > 100 || isNaN(score)) {
                console.log("invalid score, Student score can only be between 0 and 100.");
            }
        }

        scores[studentIndex][subjectIndex] = score;
        totalScore += score;
    }

    studentTotals[studentIndex] = totalScore;
    studentAverages[studentIndex] = totalScore / numofSubjects;
}

console.log("Students Grade Report");

let classTotal = 0;
let lowestScore = studentTotals[0];
let highestScore = studentTotals[0];
let leastStudent = 0;
let topStudent = 0;

for (let studentIndex = 0; studentIndex < numofStudents; studentIndex++) {
    let total = studentTotals[studentIndex];
    let average = studentAverages[studentIndex];
    console.log("Student " + (studentIndex + 1) + " Total: " + total + ", Average: " + average.toFixed(2));

    classTotal += total;

    if (total > highestScore) {
        highestScore = total;
        topStudent = studentIndex;
    }

    if (total < lowestScore) {
        lowestScore = total;
        leastStudent = studentIndex;
    }
}

let classAverage = classTotal / (numofStudents * numofSubjects);

console.log("The Class Average is : " + classAverage.toFixed(2));
console.log("Best Performing Student is : Student " + (topStudent + 1) + " (Total: " + highestScore + ")");
console.log("Lowest Performing Student is: Student " + (leastStudent + 1) + " (Total: " + lowestScore + ")");
