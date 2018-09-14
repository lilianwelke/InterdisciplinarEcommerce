function init() {
    document.querySelector('#btn-salvar').addEventListener('click', registrarMarca);
}
var URL = "http://localhost:8080/ecommerce-web/registrarmarca";
function registrarMarca(oForm) {
    var formData = {};
    formData["marca"] = document.querySelector('#marca').value;
    var http = new XMLHttpRequest();
    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            alert('Cadastro Realizado com Sucesso!');
            document.querySelector('#marca').value = '';
            console.log(this.responseText);
            parseJson(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            console.log("Erro: " + this.statusText);
        }
        if (this.readyState === 3) {
            console.log("Aguarde...");
        }
    };
    http.open("POST", URL);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(formData));
}
function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    console.log(obj.msg);
}
init();