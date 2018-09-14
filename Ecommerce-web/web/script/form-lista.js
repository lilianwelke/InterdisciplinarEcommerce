function init() {
//    fazerRequisicao("GET", "http://localhost:8080/ecommerce-web/listarprodutos", false, listarProdutos);
    
    document.querySelector('#lupa-pesquisa').addEventListener('click', chamaPesquisa);
    //fazerRequisicao(listarCategorias);
    //fazerRequisicao(listarMarcas);
        
    var menul = document.querySelector('.menul').querySelectorAll('li');
    for (var i = 0; i < menul.length; i++) {
        menul[i].addEventListener('click', chamarCategoria);
    }

}
function chamaPesquisa() {
    document.querySelector('section').innerHTML = '';
    fazerRequisicaoPesquisa("GET", "http://localhost:8080/ecommerce-web/listarprodutos", false, listarProdutosPesquisa);
}
function fazerRequisicaoPesquisa(metodo, url, assincrona, callback) {
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
    
    var produto = null;
    if (document.querySelector('.pesquisa').value !== '') { 
        produto = document.querySelector('.pesquisa').value;
    }
    
    http.open(metodo, url+ "?produto=" + produto, assincrona);
    http.setRequestHeader("Authorization", "ehaiuahdfu32ioqpeyrq");
    http.send();
}

function listarProdutosPesquisa(jsonData) {
    var jsons = jsonData.split("#GL#");
    var objProd = JSON.parse(jsons[3]);

    var dv, dvImg, img, hCinco, p, dvDetalhes, arr;
    for (var i = 0; i < objProd.length; i++)
    {
        dv = document.createElement('div');
        dv.setAttribute('class', 'produto-mini');
        document.querySelector('section').appendChild(dv);

        dvImg = document.createElement('div');
        dvImg.setAttribute('class', 'div-produto');
        dv.appendChild(dvImg);

        img = document.createElement('img');
        img.src = objProd[i]['fotoProduto'];
        img.setAttribute('class', 'foto-mini');
        dvImg.appendChild(img);

        hCinco = document.createElement('h2');
        hCinco.innerText = objProd[i]['produto'];
        hCinco.setAttribute('class', 'hCinco-mini');
        dv.appendChild(hCinco);

        p = document.createElement('p');
        p.innerText = 'R$ ' + objProd[i]['precoProduto'].toFixed(2).replace('.', ',');
        p.setAttribute('class', 'preco-mini');
        dv.appendChild(p);

        dvDetalhes = document.createElement('div');
        dvDetalhes.setAttribute('class', 'detalhes');
        dv.appendChild(dvDetalhes);
        dvDetalhes.setAttribute('id', objProd[i]['cproduto']);
        dvDetalhes.setAttribute('class', 'detalhes');
        dvDetalhes.innerText = 'VER DETALHES';
        
    }
    
    arr = document.querySelectorAll('.detalhes');
    for (var j = 0; j < arr.length; j++) {
        arr[j].addEventListener('click', chamaDetalhes);
    }    
}

function chamarCategoria() {
    fazerRequisicao("POST", "http://localhost:8080/ecommerce-web/listarprodutos", false, buscaCategoria);
}

function fazerRequisicaoCategoria(metodo, url, assincrona, callback) {
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
    
    var categoria = null;
    if (document.querySelector('.pesquisa').value !== '') { 
        produto = document.querySelector('.pesquisa').value;
    }
    
    http.open(metodo, url+ "?produto=" + produto, assincrona);
    http.setRequestHeader("Authorization", "ehaiuahdfu32ioqpeyrq");
    http.send();
}

//modelo
//function fazerRequisicao(metodo, url, assincrona, callback) {
//    var http = new XMLHttpRequest();
//    http.onreadystatechange = function ()
//    {
//        if (this.readyState === 4 && this.status === 200) {
//            console.log("Ok.");
//            callback(this.responseText);
//        }
//        if (this.readyState === 4 && this.status !== 200) {
//            console.log("Erro: " + this.statusText);
//        }
//        if (this.readyState === 3) {
//            console.log("Aguarde...");
//        }
//    };
//    http.open(metodo, url);
//    http.setRequestHeader("Authorization", "ehaiuahdfu32ioqpeyrq");
//    http.send();
//}
//function listarProdutos(jsonData) {
//    var jsons = jsonData.split("#GL#");
//    var objProd = JSON.parse(jsons[0]);
//    var objCat = JSON.parse(jsons[1]);
//    var objMarc = JSON.parse(jsons[2]);
//
//    var dv, dvImg, img, hCinco, p, dvDetalhes, arr;
//    for (var i = 0; i < objProd.length; i++)
//    {
//        dv = document.createElement('div');
//        dv.setAttribute('class', 'produto-mini');
//        document.querySelector('section').appendChild(dv);
//
//        dvImg = document.createElement('div');
//        dvImg.setAttribute('class', 'div-produto');
//        dv.appendChild(dvImg);
//
//        img = document.createElement('img');
//        img.src = objProd[i]['fotoProduto'];
//        img.setAttribute('class', 'foto-mini');
//        dvImg.appendChild(img);
//
//        hCinco = document.createElement('h2');
//        hCinco.innerText = objProd[i]['produto'];
//        hCinco.setAttribute('class', 'hCinco-mini');
//        dv.appendChild(hCinco);
//
//        p = document.createElement('p');
//        p.innerText = 'R$ ' + objProd[i]['precoProduto'].toFixed(2).replace('.', ',');
//        p.setAttribute('class', 'preco-mini');
//        dv.appendChild(p);
//
//        dvDetalhes = document.createElement('div');
//        dvDetalhes.setAttribute('class', 'detalhes');
//        dv.appendChild(dvDetalhes);
//        dvDetalhes.setAttribute('id', objProd[i]['cproduto']);
//        dvDetalhes.setAttribute('class', 'detalhes');
//        dvDetalhes.innerText = 'VER DETALHES';
//        
//    }
//    
//    arr = document.querySelectorAll('.detalhes');
//    for (var j = 0; j < arr.length; j++) {
//        arr[j].addEventListener('click', chamaDetalhes);
//    }
//    
//}

function chamaDetalhes(e) {
    window.location = 'form-detalhes.html?cproduto=' + this.id;
}

init();
