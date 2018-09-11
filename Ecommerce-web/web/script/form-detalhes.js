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
    http.open(metodo, url  + "?cproduto=" + cproduto);
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
        p4.innerText = 'Disponível: '+objDeta['qtdeEstoque'] +' unidades';
        p4.setAttribute('class', 'qtde-disp');
        dvDetalhes.appendChild(p4);
                
        dvCarrinho = document.createElement('div');
        dvCarrinho.setAttribute('class', 'comprar-agora');
        dvDetalhes.appendChild(dvCarrinho);
        dvCarrinho.innerText = 'COMPRAR AGORA';
        
//        aCarrinho = document.createElement('a');
//        aCarrinho.innerText = 'COMPRAR AGORA';
//        aCarrinho.setAttribute('href', '');
//        dvCarrinho.appendChild(aCarrinho);
//    }
}

init();