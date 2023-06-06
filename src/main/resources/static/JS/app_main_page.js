document.addEventListener("DOMContentLoaded", () => {
  const listSelect1 = document.getElementById("js-listSelect1");
  const listSelect2 = document.getElementById("js-listSelect2");
  const listCounts = document.getElementById("js-listCounts");
  const yourResponsibleId = document.getElementById("js-responsibleId").value;

  const searchBtn = document.getElementById("js-searchBtn");

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

  //並び替えボタン
  searchBtn.addEventListener("click", () => {
    //予約状況
    var selectedIndex1 = listSelect1.selectedIndex;
    var selectedOption1 = listSelect1.options[selectedIndex1];
    var selectedValue1 = selectedOption1.value;
    //向け地
    var selectedIndex2 = listSelect2.selectedIndex;
    var selectedOption2 = listSelect2.options[selectedIndex2];
    var selectedValue2 = selectedOption2.value;



    const keyWord = document.getElementById("js-searchText").value;
    console.log(selectedValue1);
    console.log(selectedValue2);
    console.log(keyWord);
    //並び替えの情報を渡す
    fetch(`/cargoSort?searchId=${yourResponsibleId}&changeMenu1=${selectedValue1}&changeMenu2=${selectedValue2}&keyword=${keyWord}`)
    .then(
      (res) => {
        //RestControllerから受け取った値->res(成功/200 失敗/400)
        if (res.status === 400) {
          console.log("no");
        } else {
          res.json().then((data) => {
            console.log(data);
            listShow(data);
          });
        }
      }
    );
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
          listShow(data);
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
    const th0 = document.createElement("th");
    th0.textContent = "ID";
    headerRow.appendChild(th0);
    const th1 = document.createElement("th");
    th1.textContent = "CLOSE-DATE";
    headerRow.appendChild(th1);
    const th2 = document.createElement("th");
    th2.textContent = "CARGO-NAME";
    headerRow.appendChild(th2);
    const th3 = document.createElement("th");
    th3.textContent = "DESTINATION";
    headerRow.appendChild(th3);
    const th4 = document.createElement("th");
    th4.textContent = "LITHIUM-STATUS";
    headerRow.appendChild(th4);
    const th5 = document.createElement("th");
    th5.textContent = "RESERVE-STATUS";
    headerRow.appendChild(th5);
    const th6 = document.createElement("th");
    th6.textContent = "INFO";
    headerRow.appendChild(th6);
    thead.appendChild(headerRow);
    //.jsonで受け取った値->data

    // テーブルボディの生成
    const tbody = document.createElement("tbody");
    data.forEach((cargo) => {
      const row = document.createElement("tr");
      for (const key in cargo) {
        const cell = document.createElement("td");
        if (key == "lithium") {
          if (cargo[key] === 1) {
            cell.textContent = "☑";
          } else {
            cell.textContent = "";
          }
        } else if (key == "reserveStatus") {
          if (cargo[key] === 1) {
            cell.textContent = "booked";
          } else {
            cell.textContent = "not yet";
          }
        } else {
          cell.textContent = cargo[key];
        }
        row.appendChild(cell);
      }
      const cell = document.createElement("td");
      cell.textContent = "INFO";
      cell.addEventListener("click", () => showProductDetails(cargo));
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
