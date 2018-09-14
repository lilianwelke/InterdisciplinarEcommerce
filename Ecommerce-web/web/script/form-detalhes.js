//var URL = "http://localhost:8080/ecommerce-web/listardetalhes";
function init() {
    fazerRequisicao("GET", "http://localhost:8080/ecommerce-web/listardetalhes", false, listarDetalhes);
}

function fazerRequisicao(metodo, url, assincrona, callback) {
    var http = new XMLHttpRequest();
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

    var cproduto = obterParametro('cproduto');
    http.open(metodo, url + "?cproduto=" + cproduto, assincrona);
    http.setRequestHeader("Authorization", "ehaiuahdfu32ioqpeyrq");
    http.send();
}

function obterParametro(nomeParametro) {
    var url = new URL(window.location.href);
    return url.searchParams.get(nomeParametro);
}

function listarDetalhes(jsonData) {
    var objDeta = JSON.parse(jsonData);

    var dv, dvImg, img, dvDetalhes, hCinco, p1, p2, p3, p4, dvCarrinho, aCarrinho;
//    for (var i = 0; i < objDeta.length; i++)
//    {
    dv = document.createElement('div');
    dv.setAttribute('class', 'produto-max');
    document.querySelector('section').appendChild(dv);

    dvImg = document.createElement('div');
    dvImg.setAttribute('class', 'div-produtoMax');
    dv.appendChild(dvImg);

    img = document.createElement('img');
    img.src = objDeta['fotoProduto'];
    img.setAttribute('class', 'foto-max');
    dvImg.appendChild(img);

    dvDetalhes = document.createElement('div');
    dvDetalhes.setAttribute('class', 'div-detalhes');
    dv.appendChild(dvDetalhes);

    p1 = document.createElement('p');
    p1.innerText = objDeta['cmarca']['marca'];
    p1.setAttribute('class', 'mrc');
    dvDetalhes.appendChild(p1);

    hCinco = document.createElement('h2');
    hCinco.innerText = objDeta['produto'];
    hCinco.setAttribute('class', 'hCinco-max');
    dvDetalhes.appendChild(hCinco);

    p2 = document.createElement('p');
    p2.innerText = objDeta['descProduto'];
    dvDetalhes.appendChild(p2);

    p3 = document.createElement('p');
    p3.innerText = 'R$ ' + objDeta['precoProduto'].toFixed(2).replace('.', ',');
    p3.setAttribute('class', 'preco-max');
    dvDetalhes.appendChild(p3);

    p4 = document.createElement('p');
    p4.innerText = 'Disponível: ' + objDeta['qtdeEstoque'] + ' unidades';
    p4.setAttribute('class', 'qtde-disp');
    dvDetalhes.appendChild(p4);

    aCarrinho = document.createElement('a');
    aCarrinho.setAttribute('href', 'form-carrinho.html');
    dvDetalhes.appendChild(aCarrinho);

    dvCarrinho = document.createElement('div');
    dvCarrinho.setAttribute('class', 'comprar-agora');
    dvCarrinho.setAttribute('id', objDeta['cproduto']);
    aCarrinho.appendChild(dvCarrinho);
    dvCarrinho.innerText = 'COMPRAR AGORA';

    document.querySelector('.comprar-agora').addEventListener('click', chamaCarrinho);

//    arr = document.querySelectorAll('.comprar-agora');
//    for (var j = 0; j < arr.length; j++) {
//        arr[j].addEventListener('click', chamaCarrinho);
//    }
}

function chamaCarrinho(e) {
//    window.location = 'form-carrinho.html?cproduto=' + this.id;
    var infos = e.target.parentNode.parentNode.parentNode;
    var carrinho;
    var fotoProduto = infos.querySelector('.foto-max').src;
    var marca = infos.querySelector('.mrc').innerText;
    var produto = infos.querySelector('.hCinco-max').innerText;
    var precoProduto = infos.querySelector('.preco-max').innerText;

    if (localStorage.carrinho) {
        carrinho = JSON.parse(localStorage.carrinho);
        localStorage.setItem('carrinho', JSON.stringify(carrinho));
    } else {
        carrinho = {'produtos': []};
    }

    carrinho.produtos.push({'cproduto': this.id, 'fotoProduto': fotoProduto, 'marca': marca, 'produto': produto, 
        'precoProduto': precoProduto, 'qtdeProduto' : ''});

    window.localStorage.setItem('carrinho', JSON.stringify(carrinho));

}

init();