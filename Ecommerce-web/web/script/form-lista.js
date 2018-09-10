//window.onload = init;
//var URL = "http://localhost:8080/ecommerce-web/listarprodutos";
function init() {
    fazerRequisicao("GET", "http://localhost:8080/ecommerce-web/listarprodutos", false, listarProdutos);
    //fazerRequisicao(listarCategorias);
    //fazerRequisicao(listarMarcas);
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
    http.open(metodo, url);
    http.setRequestHeader("Authorization", "ehaiuahdfu32ioqpeyrq");
    http.send();
}
function listarProdutos(jsonData) {
    var jsons = jsonData.split("#GL#");
    var objProd = JSON.parse(jsons[0]);
    var objCat = JSON.parse(jsons[1]);
    var objMarc = JSON.parse(jsons[2]);

    var dv, dvImg, img, hCinco, p, dvDetalhes, aDetalhes;
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

        aDetalhes = document.createElement('a');
        aDetalhes.innerText = 'VER DETALHES';
        aDetalhes.setAttribute('href', 'form-detalhes.html');
        dvDetalhes.appendChild(aDetalhes);

        //como pegar o cproduto de outra pg
        document.querySelector('.detalhes').addEventListener('click', chamaDetalhes);
    }

}

function chamaDetalhes(e) {
    window.location = 'form-detalhes.html?cproduto=' + this.id;
}

init();
