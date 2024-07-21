'use strict';

var b ="helloworld";

console.log(b.substring(5,10));
console.log(b.toUpperCase());

var c=["amy","bobi",5,7];
console.log(c.sort());

var person={
    name: "Bob",
    age: 20,
    gender: "M"
}
for(var i in person){
    console.log(`${i}:${person[i]}`);
}
console.log('school' in person);
function a() {
    var b=["apple","banana","pear","peach"];
    for(var a=0;a<b.length;a++){
        var c=`你好,${person.name}\n今年${person.age}岁\n性别:${person.gender}\n你看看喜欢什么水果:${b[a].toUpperCase()}`;
        console.log(c);
        //console.log(person.name+"\n"+person.age+"\n"+person.gender+"\n"+"你看看是什么水果:"+b[a]);
    }
}
a();

console.log(`haha
this is a 
game`);

var arr=[[1,2,3],[400,500,600],"-"];

var ar=arr[1][1];
console.log(ar);

var aar=['小明','小红','大军','阿黄'];
var aa=aar.sort();
console.log(`欢迎${aa.slice(0,3)}和${aa[3]}同学`);

var num=[10,12,15,18];
num.forEach(function(n){
    if(n<12){
        console.log("这个是小于12");
    }else if(10<n&&n<15){
        console.log("这个是大于12，小于15");
    }else{
        console.log("其他的");
    }
});

function submitForm(){
    var username=document.getElementById("username").value;
    alert("helle "+username+"!");
}
var x=1;
var i;
for(i=1;i<=10;i++){
    x=x*i;
}
if(x===3628800){
    console.log('1*2*3*4*5*6*7*8*9*10='+x);
}else{
    console.log('计算错误');
}

var ff=['apple','google','microsoft'];
var q,w;
for(q=0;q<ff.length;q++){
    w=ff[q];
    console.log(w);
}

var m=new Map([['mike',2],['bob',3],['alice',1]]);
m.set('petty',2);
console.log(m.has('petty'));
m.forEach(function(value,key){
    console.log(`这个是value${value},这个是key${key}`);
});

var s=new Set([2,3,'ff',6]);
s.add('hello');
console.log(s);

function abs(cc){
    if(typeof cc!=='number'){
        throw 'Not a number';
    }
    for(var a=0;a<arguments.length;a++){
    if(arguments[a]>59){
        console.log( `"这个是满分",${arguments[a]}`);
    }else{
        console.log(`'这个低于60'${arguments[a]}`);
    }
}
}
abs(77,48,66,80);

function foo(a,b,...rest) {
    console.log('a'+a);
    console.log('b'+b);
    console.log(rest);
}
foo(66,'jj',57,9,'55');

function radius(r,pi){
    if(arguments.length===1){
        return r*r*3.14;
    }else {
        return r*r*arguments[1];
    }
}
if(radius(2)===12.56 && radius(2,3.1416)===12.5664){
    console.log('测试通过');
}else{
    console.log('测试失败');
}


function foor() {
    var x = 'Hello, ' + y;
    console.log(x);
    var y = 'Bob';
}

foor();

