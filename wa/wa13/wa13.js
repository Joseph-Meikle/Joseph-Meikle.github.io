//problem 1
const employee1 = {
    first_name:"Sam",
    department:"Tech",
    designation:"Position",
    salary:40000,
    raise_eligible: true
};
const employee2 = {
    first_name:"Mary",
    department:"Finance",
    designation:"Trainee",
    salary:18500,
    raise_eligible:true
};
const employee3 = {
    first_name:"Bill",
    department:"HR",
    designation:"Executive",
    salary:21200,
    raise_eligible:false
};
const employee4 = {
    first_name:"Anna",
    department:"Tech",
    designation:"Executive",
    salary:25600,
    raise_eligible:false
};
console.log(employee1);
console.log(employee2);
console.log(employee3);
//problem 2
const company = {
    companyName:"Tech Stars",
    website:"www.techstars.site",
    employees:[employee1,employee2,employee3,employee4]
};
console.log(company);
//problem 3 (updating problems 1 and 2)
console.log(employee4);
//problem 4
var totalSalary = 0;
for (const emp of company.employees){
    totalSalary+=emp.salary;
}
console.log("Total salary: "+totalSalary);
//problem 5
function giveRaises(){
    for (const emp of company.employees){
        if (emp.raise_eligible){
            emp.salary*=1.1;//raise by 10%
            emp.raise_eligible=false;
        }
    }
}
console.log("raises given:");
console.log(employee1);
console.log(employee2);
console.log(employee3);
console.log(employee4);
//problem 6
const workingFromHome=['Anna', 'Sam'];
for (const emp of company.employees){
    emp["wfh"]=workingFromHome.includes(emp.first_name);
}
console.log("wfh added:");
console.log(employee1);
console.log(employee2);
console.log(employee3);
console.log(employee4);
