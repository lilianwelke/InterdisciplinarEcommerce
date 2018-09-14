function init() {
    document.querySelector('#continuar-compra').addEventListener('click', chamarCliente);
    document.querySelector('#confirmar-compra').addEventListener('click', confirmarCompra);

}
var cpf, clienteSave, pedidoSave;
function fazerRequisicao(metodo, url, assincrona, callback) {
    var http = new XMLHttpRequest();
    var cliente = {};
    cliente["cpf"] = (cpf > 0 ? cpf :
            document.querySelector('.cpf-div').querySelector('input[type=text').value);
    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log("Ok.");
            callback(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            console.log("Erro: " + this.statusText);
        }
        if (this.readyState === 3) {
            console.log("Aguarde...");
        }
    };

    http.open(metodo, url, assincrona);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(cliente));
}

function chamarCliente() {
    fazerRequisicao("POST", "http://localhost:8080/ecommerce-web/listarclientes", false, verificarCliente);
}

function verificarCliente(jsonData) {
    var objPed = {};
    objPed = JSON.parse(jsonData);
    document.querySelector('.cabecalhoCar').style.display = 'none';

    if (objPed['ccliente'] === 0 && !(cpf > 0)) {
        document.querySelector('.conteudo').style.display = 'block';
        document.querySelector('#btn-salvar').addEventListener('click', chamarRegistra);
    } else {
        document.querySelector('.cabecalhoConf').style.display = 'block';
        var dv = document.querySelector('.confirmar-div');
        var p = document.createElement('p');
        dv.appendChild(p);
        p.innerText = 'Oi ' + objPed["cliente"] + '!';
        var p = document.createElement('p');
        dv.appendChild(p);
        p.innerText = 'Confirme a sua compra.';
        clienteSave = objPed["ccliente"];
    }
}

function chamarRegistra() {
    registrarCliente("POST", "http://localhost:8080/ecommerce-web/registrarcliente", false);
}

function registrarCliente(metodo, url, assincrona) {
    var cliente = {};
    cpf = document.querySelector('#cpf').value;
    cliente["cliente"] = document.querySelector('#nome').value;
    cliente["cpf"] = document.querySelector('#cpf').value;
    cliente["endereco"] = document.querySelector('#endereco').value;
    cliente["cidade"] = document.querySelector('#cidade').value;
    cliente["cep"] = document.querySelector('#cep').value;
    cliente["uf"] = document.querySelector('#uf').value;
    cliente["telefone"] = document.querySelector('#telefone').value;
    var http = new XMLHttpRequest();
    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log("Ok.");
            console.log(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            console.log("Erro: " + this.statusText);
        }
        if (this.readyState === 3) {
            console.log("Aguarde...");
        }
    };
    http.open(metodo, url, assincrona);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(cliente));

    document.querySelector('.conteudo').style.display = 'none';
    document.querySelector('.cabecalhoConf').style.display = 'block';

    chamarCliente();

}

function confirmarCompra() {
    registrarPedido("POST", "http://localhost:8080/ecommerce-web/registrarpedido", false);
}

function registrarPedido(metodo, url, assincrona) {
    var objPed = {};
    objPed = window.localStorage.getItem('carrinho');
    objPed = JSON.parse(objPed);

    var pedido = {};

    pedido["ccliente"] = clienteSave;

    var tot = 0.0;
    for (var i = 0; i < objPed['produtos'].length; i++) {
        tot += parseFloat(objPed['produtos'][i]['qtdeProduto']) *
                parseFloat(objPed['produtos'][i]['precoProduto'].replace(',', '.').replace('R$', '').trim());
    }

    pedido["totalCompra"] = tot;
    pedido["pagamento"] = 'À vista';
    pedido["concluida"] = 'S';
    pedido["frete"] = 10.00;
    var http = new XMLHttpRequest();
    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log("Pedido Ok.");
            console.log(this.responseText);
            getPedido(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            console.log("Erro: " + this.statusText);
        }
        if (this.readyState === 3) {
            console.log("Aguarde...");
        }

    };
    http.open(metodo, url, assincrona);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(pedido));

}

function getPedido(jsonData) {
    var obj = JSON.parse(jsonData);
    pedidoSave = obj["cPedido"];
    registrarItemPedido("POST", "http://localhost:8080/ecommerce-web/registraritempedido", false);
    document.querySelector('.cabecalhoConf').style.display = 'none';
    document.querySelector('.cabecalhoSucesso').style.display = 'block';
    localStorage.clear();
}

function registrarItemPedido(metodo, url, assincrona) {
    var objItemPed = {};
    objItemPed = window.localStorage.getItem('carrinho');
    objItemPed = JSON.parse(objItemPed);
    var itemPedido = {};
    for (var i = 0; i < objItemPed['produtos'].length; i++) {
        itemPedido = {};

        itemPedido["cpedido"] = pedidoSave;
        itemPedido["cproduto"] = parseInt(objItemPed['produtos'][i]['cproduto']);
        itemPedido["precoProduto"] = parseFloat(objItemPed['produtos'][i]['precoProduto'].replace(',', '.').replace('R$', '').trim());
        itemPedido["qtdeProduto"] = objItemPed['produtos'][i]['qtdeProduto'];

        var http = new XMLHttpRequest();
        http.onreadystatechange = function ()
        {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("result").innerHTML = "Ok.";
                console.log(this.responseText);
                parseJson(this.responseText);
            }
            if (this.readyState === 4 && this.status !== 200) {
                document.getElementById("result").innerHTML = "Erro: " + this.statusText;
            }
            if (this.readyState === 3) {
                document.getElementById("result").innerHTML = "Aguarde...";
            }
        };
        http.open(metodo, url, assincrona);
        http.setRequestHeader("Content-type", "application/json");
        http.send(JSON.stringify(itemPedido));
    }
}

init();