document.addEventListener("DOMContentLoaded", () => {
  const listSelect1 = document.getElementById("js-listSelect1");
  const listSelect2 = document.getElementById("js-listSelect2");
  const listCounts = document.getElementById("js-listCounts");
  const yourResponsibleId = document.getElementById("js-responsibleId").value;

  //絞り込みデータ（予約）
  const bookStatus = {
    1: "booked",
    2: "not yet",
  };
  for (const key in bookStatus) {
    const option1 = document.createElement("option");
    option1.value = key;
    option1.text = bookStatus[key];
    listSelect1.appendChild(option1);
  }

  //絞り込みデータ（向け地）
  fetch(`/destination?searchId=${yourResponsibleId}`).then((res) => {
    //RestControllerから受け取った値->res(成功/200 失敗/400)
    if (res.status === 400) {
      console.log("no");
    } else {
      res.json().then((data) => {
        console.log(data);
        data.forEach((dataEach) => {
          const option1 = document.createElement("option");
          option1.value = dataEach.id;
          option1.text = dataEach.name;
          listSelect2.appendChild(option1);
        });
      });
    }
  });

  /**
   * 初期のリストの生成
   */
  function listProductShow() {
    fetch(`/cargos?searchId=${yourResponsibleId}`).then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log("no");
      } else {
        //.jsonは非同期処理(.jsonの受け取り)
        res.json().then((data) => {
            console.log(data);
          //listShow(data);
        });
      }
    });
  }

  // 詳細情報の表示
  function showProductDetails(product) {
    console.log("click");

    //IDを検索と受け渡し
    fetch(`/product?searchId=${product.productId}`).then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log("no");
      } else {
        res.json().then((data) => {
          window.location.href = `/product-detail/${data.id}`; //ControllerのGetに指示を出す
        });
      }
    });
  }

  //リスト表示用
  function listShow(data) {
    let counts = 0;
    // テーブルをHTMLに追加
    const tableContainer = document.getElementById("tableContainer");
    tableContainer.innerHTML = "";
    // テーブル要素の生成
    const table = document.createElement("table");
    // テーブルヘッダーの生成
    const thead = document.createElement("thead");
    const headerRow = document.createElement("tr");
    const th1 = document.createElement("th");
    th1.textContent = "商品ID";
    headerRow.appendChild(th1);
    const th2 = document.createElement("th");
    th2.textContent = "商品名";
    headerRow.appendChild(th2);
    const th3 = document.createElement("th");
    th3.textContent = "値段";
    headerRow.appendChild(th3);
    const th4 = document.createElement("th");
    th4.textContent = "カテゴリ";
    headerRow.appendChild(th4);
    const th5 = document.createElement("th");
    th5.textContent = "詳細";
    headerRow.appendChild(th5);
    thead.appendChild(headerRow);
    //.jsonで受け取った値->data

    // テーブルボディの生成
    const tbody = document.createElement("tbody");
    data.forEach((product) => {
      const row = document.createElement("tr");
      for (const key in product) {
        const cell = document.createElement("td");
        cell.textContent = product[key];
        row.appendChild(cell);
      }
      const cell = document.createElement("td");
      cell.textContent = "詳細";
      cell.addEventListener("click", () => showProductDetails(product));
      cell.classList.add("detailBtn");

      row.appendChild(cell);
      counts++;
      tbody.appendChild(row);
    });
    // テーブルにヘッダーとボディを追加
    table.appendChild(thead);
    table.appendChild(tbody);

    tableContainer.appendChild(table);
    listCounts.textContent = counts;
  }

  listProductShow();
});
