function Book(name, author, isbn){
    this.name = name;
    this.author = author;
    this.isbn = isbn;

}
var path = "http://localhost:8080/HelloWorld";
var addBook = function(){
    var name = document.getElementById('name');
    var author = document.getElementById('author');
    var isbn = document.getElementById('isbn');
    var addingBook = new Book(name,author,isbn);
    validate(addingBook);
    var xhr = new XMLHttpRequest();
    var path = path + "/library/books";
    xhr.open("POST",path,true);
    xhr.send(JSON(addingBook));//TODO send Json object to server
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