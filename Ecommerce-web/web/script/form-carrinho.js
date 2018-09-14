function init() {
    listarCarrinho();
}

function listarCarrinho() {
    var objCarr = {};
    objCarr = window.localStorage.getItem('carrinho');
    objCarr = JSON.parse(objCarr);

    var dv, h2, dvProd, table, thead, tbody, tr, th, td, dvFinalizar, dvImg, img, inpt, spn, iaws, arr, cspn, a;
    dv = document.createElement('div');
    dv.setAttribute('class', 'cabecalhoCar');
    document.querySelector('section').appendChild(dv);

    h2 = document.createElement('h2');
    h2.innerText = 'Carrinho';
    dv.appendChild(h2);

    dvProd = document.createElement('div');
    dvProd.setAttribute('class', 'produto-max');
    dv.appendChild(dvProd);

    if (objCarr !== null && objCarr.produtos.length !== 0) {

        table = document.createElement('table');
        dvProd.appendChild(table);

        thead = document.createElement('thead');
        table.appendChild(thead);

        tr = document.createElement('tr');
        thead.appendChild(tr);

        th = document.createElement('th');
        tr.appendChild(th);
        th.innerText = 'Produto';
        th.setAttribute('class', 'th-prod');
        th.setAttribute('colspan', '2');

        th = document.createElement('th');
        tr.appendChild(th);
        th.innerText = 'Preço';

        th = document.createElement('th');
        tr.appendChild(th);
        th.innerText = 'Quantidade';

        th = document.createElement('th');
        tr.appendChild(th);
        th.innerText = 'Total';

        tbody = document.createElement('tbody');
        table.appendChild(tbody);

        for (var i = 0; i < objCarr.produtos.length; i++)
        {
            tr = document.createElement('tr');
            tr.setAttribute('id', objCarr['produtos'][i]['cproduto']);
            tr.setAttribute('class', 'produto');
            tbody.appendChild(tr);

            td = document.createElement('td');
            td.setAttribute('class', 'td-img');
            tr.appendChild(td);

            dvImg = document.createElement('div');
            dvImg.setAttribute('class', 'div-produtoCar');
            td.appendChild(dvImg);

            img = document.createElement('img');
            img.setAttribute('class', 'foto-max');
            img.src = objCarr['produtos'][i]['fotoProduto'];
            dvImg.appendChild(img);

            td = document.createElement('td');
            td.setAttribute('class', 'td-prod');
            tr.appendChild(td);
            td.innerText = objCarr['produtos'][i]['marca'];
            td.appendChild(document.createElement('br'));
            td.innerText += objCarr['produtos'][i]['produto'];

            td = document.createElement('td');
            tr.appendChild(td);
            td.innerText = objCarr['produtos'][i]['precoProduto'];

            td = document.createElement('td');
            tr.appendChild(td);
            td.setAttribute('class', 'td-qtde');

            inpt = document.createElement('input');
            td.appendChild(inpt);
            inpt.setAttribute('class', 'qtde-compra');
            inpt.setAttribute('type', 'number');
            inpt.setAttribute('value', '1');

            td = document.createElement('td');
            tr.appendChild(td);
            td.innerText = objCarr['produtos'][i]['precoProduto'];

            spn = document.createElement('span');
            td.appendChild(spn);
            spn.setAttribute('class', 'remover-pedido');

            iaws = document.createElement('i');
            spn.appendChild(iaws);
            iaws.setAttribute('class', 'fas fa-times');

            cspn = document.createElement('span');
            tr.appendChild(cspn);
            cspn.style.display = 'none';
            cspn.id = 'cproduto';
            cspn.innerText = objCarr['produtos'][i]['cproduto'];

        }

        arr = document.querySelectorAll('.remover-pedido');
        for (var j = 0; j < arr.length; j++) {
            arr[j].addEventListener('click', removerItem);
        }

        a = document.createElement('a'); 
        dvProd.appendChild(a);
        a.setAttribute('href', 'form-pedido.html');

        dvFinalizar = document.createElement('div');
        dvFinalizar.setAttribute('id', 'finalizar-compra');
        a.appendChild(dvFinalizar);
        dvFinalizar.innerText = 'FINALIZAR COMPRA';

        document.querySelector('#finalizar-compra').parentNode.addEventListener('click', fazerPush);
        
    }

    function fazerPush(e) {
        var objCarr = {};
        objCarr = window.localStorage.getItem('carrinho');
        objCarr = JSON.parse(objCarr);
        
        var qtdes = document.querySelectorAll('.qtde-compra');
            
        for (var i = 0; i < objCarr.produtos.length; i++) { 
            objCarr['produtos'][i]['qtdeProduto'] = qtdes[i].value;
        }
        window.localStorage.setItem('carrinho', JSON.stringify(objCarr));
        console.log(objCarr);
    }

    function removerItem(e) {
        var objCarr = {};
        objCarr = window.localStorage.getItem('carrinho');
        objCarr = JSON.parse(objCarr);

        var produto = e.target.parentNode.parentNode.parentNode;
        produto.classList.remove('produto');

        var cRemove;

        var cproduto = produto.querySelector('#cproduto').innerHTML;

        for (var i = 0; i < objCarr.produtos.length; i++) {
            if (cproduto === objCarr['produtos'][i]['cproduto']) {
                cRemove = cproduto;
                var prodRemovido = objCarr.produtos.find(function (obj, idx, arr) {
                    if (obj.cproduto === cRemove) {
                        arr.splice(idx, 1);
                        return true;
                    } else {
                        return false;
                    }
                });
            }
        }
        window.localStorage.setItem('carrinho', JSON.stringify(objCarr));
        produto.innerHTML = "";

        if (objCarr === null && objCarr.produtos.length === 0) {
            document.querySelector('table').style.display = 'none';
            document.querySelector('#finalizar-compra').style.display = 'none';
        }
    }

}

function chamaFinalizar(e) {
    window.location = 'form-carrinho.html?cproduto=' + this.id;
}

init();
