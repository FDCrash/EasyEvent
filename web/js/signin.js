// Get the modal
var modal = document.getElementById('id01');
var login = document.getElementById('btn_submit');

// When the user clicks anywhere outside of the modal,  close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

function signIn() {
    let dto = {
        "login": $("#inpt_login").val(),
        "password": $("#inpt_password").val()
    };
    // let url = 'http://localhost:8080/users/auth';
    // let role =  fetch(url, {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify(dto)
    // }).then(response => response.json())
    // debugger;
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/users/auth",
        dataType: "json",
        crossDomain: false,
        data: JSON.stringify(dto),
        contentType : "application/json; charset=utf-8",
        success: function(data){alert(JSON.stringify(data));},
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
}

// window.onclick = function (event){
//     if(event.target == login){
//         let dto = {
//             "login": $("#inpt_login").val(),
//             "password": $("#inpt_password").val()
//         };
//         // let url = 'http://localhost:8080/users/auth';
//         // let role =  fetch(url, {
//         //     method: 'POST',
//         //     headers: {
//         //         'Content-Type': 'application/json;charset=utf-8'
//         //     },
//         //     body: JSON.stringify(dto)
//         // }).then(response => response.json())
//         // .then(result => alert(JSON.stringify(dto)))
//         $.ajax({
//             type: "POST",
//             url: "http://localhost:8080/users/auth",
//             data: JSON.stringify(dto),
//             contentType : 'application/json',
//             dataType: "json",
//             success: function (data) {
//                 debugger;
//                 console.log(data);
//             }
//         });
//     }
// };


// $("#btn_submit").click(function () {
//     let dto = {
//         "login": $("#inpt_login").val(),
//         "password": $("#inpt_password").val()
//     };
//     // let url = 'http://localhost:8080/users/auth';
//     // let role =  fetch(url, {
//     //     method: 'POST',
//     //     headers: {
//     //         'Content-Type': 'application/json;charset=utf-8'
//     //     },
//     //     body: JSON.stringify(dto)
//     // }).then(response => response.json())
//     // .then(result => alert(JSON.stringify(dto)))
//
//     $.ajax({
//         type: "POST",
//         url: "http://localhost:8080/users/auth",
//         data: JSON.stringify(dto),
//         contentType : 'application/json',
//         success: function (data) {
//             console.log(data);
//         },
//         dataType: "json"
//     });
// });
