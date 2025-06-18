let books = [];
let myChart;

const bookForm = document.getElementById("book-form");
const bookContainer = document.getElementById("books-container");
const sortDropdown = document.getElementById("sort");
const editDialog = document.getElementById("edit-dialog");
const cancelDialog = document.getElementById("cancel-dialog");
const search = document.getElementById("search");
const showChartBttn = document.getElementById("show-chart");
const addCommentBttn = document.getElementById("post-comments");

const colors = [
    "rgba(0,200,255,0.6)",
    "rgba(0,0,255,0.8)",
    "rgba(255,0,0,0.7)",
    "rgba(255,0,255,0.5)",
    "rgba(0,255,0,0.4)",
    "rgba(100,250,0,0.8)"
]

const xLabels = [
    "fiction",
    "non-fiction",
    "science-fiction",
    "fantasy",
    "science-fantasy",
    "science"
]

bookForm.addEventListener("submit", function(e) {
    e.preventDefault();

    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const publishDate = document.getElementById("publish-date").value;
    const price = document.getElementById("price").value;
    const genre = document.getElementById("genre").value;

    const book = {
        id: Date.now(),
        title: title,
        author: author,
        publishDate: publishDate,
        price: price,
        genre: genre,
        rating: 0,
        dateAdded: new Date()
    };

    books.push(book);
    saveToLocalStorage();

    bookForm.reset();

    displayBooks();

    showChartBttn.click();
});

function displayBooks() {
    bookContainer.innerHTML = "";

    if (books.length === 0) {
        bookContainer.innerHTML = `<p> No books yet. Add your first book!</p>`;
        return;
    }
    
    books.forEach(book => {
        const bookRow = document.createElement(`tr`);
        bookRow.innerHTML = `
        <th>${book.title}</th>
        <td>${book.author}</td>
        <td>${book.publishDate}</td>
        <td>${book.genre}</td>
        <td>${createStarRating(book.id, book.rating)}</td>
        <td><button onclick="deleteBook(${book.id})" id="delete-book">Delete</button></td>
        <td><button onclick="editBook(${book.id})">Edit</button></td>
        `;

        bookContainer.appendChild(bookRow);
    })
}

function addRating(id, rating) {
    const book = books.find(book => book.id === id);

    if (book) {
        book.rating = rating;
        saveToLocalStorage();
        displayBooks();
    }
}

function createStarRating(bookId, rating) {
    const maxRating = 5;
    let starString = '';
    let count = 1;

    for (; count <= maxRating; count++) {
        starString += `<span class='${count <= rating ? 'gold-star': ''}' 
        onclick='addRating(${bookId}, ${count})'>&#9733;</span>`;
    }

    return starString;
}

function deleteBook(id) {
    books = books.filter(book => book.id !== id);
    saveToLocalStorage();
    displayBooks();
    showChartBttn.click();
}


// Completed
function editBook(id) {
    const updateBook = document.getElementById("update-book");
    editDialog.showModal();
    const book = books.find(book => book.id === id);

    if (book) {
        const updateTitle = document.getElementById("update-title");
        const updateAuthor = document.getElementById("update-author");
        const updatePublishDate = document.getElementById("update-publish-date");
        const updatePrice = document.getElementById("update-price");
        const updateGenre = document.getElementById("update-genre");

        updateTitle.value = book.title;
        updateAuthor.value = book.author;
        updatePrice.value = book.price;
        updateGenre.value = book.genre;

        for (let i = 0; i < books.length; i++) {
            if (books[i].id === book.id) {
                updateBook.addEventListener("click", (e) => {
                    e.preventDefault();

                    books[i].title = updateTitle.value;
                    books[i].author = updateAuthor.value;
                    books[i].publishDate = updatePublishDate.value;
                    books[i].price = updatePrice.value;
                    books[i].genre = updateGenre.value;
                    saveToLocalStorage();
                    displayBooks();
                    showChartBttn.click();
                    editDialog.close();                
                })
            }
        }
    }

    else {
        return;
    }

}

// Completed
sortDropdown.addEventListener("change", function() {
    if (books.length === 0) {
        return;
    }

    books.sort((a, b) => {
        let firstValue = '';
        let secondValue = '';

        if (sortDropdown.value === "title") {
            firstValue = a.title.toLowerCase();
            secondValue = b.title.toLowerCase();
        }
        else if (sortDropdown.value === "author") {
            firstValue = a.author.toLowerCase();
            secondValue = b.author.toLowerCase();
        }

        if (firstValue < secondValue) {
            return -1;
        }
        if (firstValue > secondValue) {
            return 1;
        }

        return 0;
    });

    displayBooks();
});

// Completed
cancelDialog.addEventListener("click", () => {
    editDialog.close();
});

const searchBooks = () => {
    const searchTerm = document.getElementById("search").value.toLowerCase();

    const filteredBooks = books.filter(book => 
        book.title.toLowerCase().includes(searchTerm) || 
        book.author.toLowerCase().includes(searchTerm) ||
        book.genre.toLowerCase().includes(searchTerm)
    );

    displayFilteredBooks(filteredBooks);
};

const displayFilteredBooks = (filteredBooks) => {
    const searchResults = document.getElementById("search-results");

    searchResults.innerHTML = "";

    if (filteredBooks.length === 0) {
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = "No results found";
        searchResults.appendChild(errorMessage);
        return;
    }

    const searchList = document.createElement("ol");
    searchList.setAttribute("type", "I");

    filteredBooks.forEach(book => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
        <div>
            <h4>${book.title}</h4>
            <h5>${book.author}</h5>
            <p>${book.publishDate}</p>
            <p>${book.genre}</p>
            <p>${book.rating}</p>
        </div>
        `;

        searchList.appendChild(listItem);
    })

    searchResults.appendChild(searchList);
};

search.addEventListener("keypress", function(e) {
    if (e.key === "Enter") {
        e.preventDefault();
        searchBooks();
    }
    
});

function saveToLocalStorage() {
    localStorage.setItem('bookHubBooks', JSON.stringify(books));
}

function loadFromLocalStorage() {
    const saved = localStorage.getItem('bookHubBooks');

    if (saved) {
        books = JSON.parse(saved);

        books.forEach(book => book.dateAdded = new Date(book.dateAdded));
        displayBooks();
    }
}

/* Event listener for showChartBttn with function. If a chart was shown before, destroy the chart to prevent stacked charts.
   search through each genre of the books array and increment their counters. Calculate total and push counter percent
   into yValues array to use with the chart's properties. Chart is a doughnut with xLabels showing genre, datasets shows
   colors and the counter percentage, displayed with the text for genres.*/
showChartBttn.addEventListener("click", function createGenrePercentage(id) {
    const yValues = [];
    let fictionCounter = 0;
    let nonFictionCounter = 0;
    let scienceFictionCounter = 0;
    let fantasyCounter = 0;
    let scienceFantasyCounter = 0;
    let scienceCounter = 0;

    if (myChart) {
        myChart.destroy();
    }

    books.forEach(bookId => {
        if (bookId.genre === "fiction") {
            fictionCounter += 1;
        }
        else if (bookId.genre === "non-fiction") {
            nonFictionCounter += 1;
        }
        else if (bookId.genre === "science-fiction") {
            scienceFictionCounter += 1;
        }
        else if (bookId.genre === "fantasy") {
            fantasyCounter += 1;
        }
        else if (bookId.genre === "science-fantasy") {
            scienceFantasyCounter += 1;
        }
        else {
            scienceCounter += 1;
        }
    })

    const total = fictionCounter + 
                  nonFictionCounter + 
                  scienceFictionCounter + 
                  fantasyCounter + 
                  scienceFantasyCounter + 
                  scienceCounter;

    yValues.push(parseInt(fictionCounter / total * 100));
    yValues.push(parseInt(nonFictionCounter / total * 100));
    yValues.push(parseInt(scienceFictionCounter / total * 100));
    yValues.push(parseInt(fantasyCounter / total * 100));
    yValues.push(parseInt(scienceFantasyCounter / total * 100));
    yValues.push(parseInt(scienceCounter / total * 100));

    myChart = new Chart("doughnut-chart", {
            type: "doughnut",
            data: {
                labels: xLabels,
                datasets: [{
                    backgroundColor: colors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "Genre percentage"
                }
            }

        });
})

addCommentBttn.addEventListener("submit", function (e) {
    e.preventDefault();

    const userName = document.getElementById("username");
    const email = document.getElementById("email");
    const textArea = document.getElementById("text-area");
    const commentSection = document.getElementById("comment-section");
    const createList = document.createElement("ul");

    createList.innerHTML = '';

    createList.innerHTML += `<li class='feature-card'>
                                <h4 class='comment-el'>${userName.value}</h4>
                                <h5 class='comment-el'>${email.value}</h5>
                                <p class='comment-el'>${textArea.value}</p>
                            </li>`

    commentSection.appendChild(createList);

    addCommentBttn.reset();
});

window.addEventListener("DOMContentLoaded", loadFromLocalStorage());
window.addEventListener("DOMContentLoaded", displayBooks());