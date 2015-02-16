function Book(name, author, isbn){
    this.name = name;
    this.author = author;
    this.isbn = isbn;

}
var path = "http://localhost:8080/HelloWorld";
var addBook = function(){
    var name = document.getElementById('name').value;
    var author = document.getElementById('author').value;
    var isbn = document.getElementById('isbn').value;
    var addingBook = new Book(name,author,isbn);
    validate(addingBook);
    var xhr = new XMLHttpRequest();
    var path = "http://localhost:8080/HelloWorld/library/books/add";

    var jsonObject = JSON.stringify(addingBook);
    xhr.open("POST",path,true);
    xhr.setRequestHeader('Content-type','application/json');
    xhr.send(jsonObject);//TODO send Json object to server
}
var getBook = function (){
    var bookId = document.getElementById('bookId');
    var xhr = new XMLHttpRequest();
    var path = path + "/library/books/"+bookId;
    xhr.open("GET",path,true);
    xhr.send('');
}
var validate = function(Book){
    if( typeof Book.isbn === Number){
        if(Book.isbn.length < 5){
            return "ISBN should be more than 4 numbers";
        } else {
            return true;
        }
    } else {
        return "ISBN should be a number!";
    }
}
var bookFilter = new Array();
bookFilter[0] = "name";
bookFilter[1] = "author";
bookFilter[2] = "isbn";