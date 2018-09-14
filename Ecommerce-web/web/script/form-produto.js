function init() {
    document.querySelector('#btn-salvar').addEventListener('click', registrarProduto);
}
var URL = "http://localhost:8080/ecommerce-web/registrarproduto";
function registrarProduto(oForm) {
    var formData = {};

    formData["ccategoria"] = parseInt(document.querySelector('#ccategoria').value);
    formData["cmarca"] = parseInt(document.querySelector('#cmarca').value);
    formData["produto"] = document.querySelector('#produto').value;
    formData["descProduto"] = document.querySelector('#descProduto').value;

    var reader = new FileReader();
    reader.readAsDataURL(document.querySelector('#fotoProduto').files[0]);
    reader.onload = function () {
        console.log(reader.result);
        formData["fotoProduto"] = reader.result;
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };

    formData["precoProduto"] = parseFloat(document.querySelector('#precoProduto').value);
    formData["promocao"] = parseFloat(document.querySelector('#promocao').value);
    formData["qtdeEstoque"] = parseInt(document.querySelector('#qtdeEstoque').value);

    var http = new XMLHttpRequest();
    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            alert('Cadastro Realizado com Sucesso!');
            document.querySelector('#ccategoria').value = '';
            document.querySelector('#cmarca').value = '';
            document.querySelector('#produto').value = '';
            document.querySelector('#descProduto').value = '';
            document.querySelector('#precoProduto').value = '';
            document.querySelector('#promocao').value = '';
            document.querySelector('#qtdeEstoque').value = '';
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