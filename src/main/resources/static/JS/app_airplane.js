document.addEventListener("DOMContentLoaded", () => {
  //ボタン
  const deleteBtn = document.getElementById("js-deleteBtn");
  const insertBtn = document.getElementById("js-insertBtn");

  //追加用テーブル
  const tableContainer = document.getElementById("js-insertTable");
  tableContainer.innerHTML = "";
  const table = document.createElement("table");

  // テーブルヘッダーの生成
  const thead = document.createElement("thead");
  const headerRow = document.createElement("tr");
  const th1 = document.createElement("th");
  th1.textContent = "PREFIX";
  headerRow.appendChild(th1);
  const th2 = document.createElement("th");
  th2.textContent = "LETTER-CIDE";
  headerRow.appendChild(th2);
  const th3 = document.createElement("th");
  th3.textContent = "NAME";
  headerRow.appendChild(th3);
  const th4 = document.createElement("th");
  th4.textContent = "COUNTRY";
  headerRow.appendChild(th4);
  const th5 = document.createElement("th");
  th5.textContent = "MON";
  headerRow.appendChild(th5);
  const th6 = document.createElement("th");
  th6.textContent = "TUE";
  headerRow.appendChild(th6);
  const th7 = document.createElement("th");
  th7.textContent = "WED";
  headerRow.appendChild(th7);
  const th8 = document.createElement("th");
  th8.textContent = "THU";
  headerRow.appendChild(th8);
  const th9 = document.createElement("th");
  th9.textContent = "FRI";
  headerRow.appendChild(th9);
  const th10 = document.createElement("th");
  th10.textContent = "SAT";
  headerRow.appendChild(th10);
  const th11 = document.createElement("th");
  th11.textContent = "SUN";
  headerRow.appendChild(th11);
  thead.appendChild(headerRow);

  const tbody = document.createElement("tbody");
  const tr = document.createElement("tr");

  // 各列に対応するセルにinputタグを追加
  for (let i = 0; i < 11; i++) {
    const input = document.createElement("input");
    input.id = `input${i + 1}`;

    const td = document.createElement("td");
    td.appendChild(input);

    tr.appendChild(td);
  }

  tbody.appendChild(tr);

  table.appendChild(thead);
  table.appendChild(tbody);
  tableContainer.appendChild(table);

  let checkedData = null;

  //airplaneテーブルの情報取得
  function listAirplaneShow() {
    fetch("/airplanes").then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log("no");
      } else {
        //.jsonは非同期処理(.jsonの受け取り)
        res.json().then((data) => {
          //.jsonで受け取った値->data
          // テーブル要素の生成
          const tableContainer = document.getElementById("js-tableContainer");
          tableContainer.innerHTML = "";
          const table = document.createElement("table");

          // テーブルヘッダーの生成
          const thead = document.createElement("thead");
          const headerRow = document.createElement("tr");
          //チェックボックスの生成
          const checkbox = document.createElement("input");
          checkbox.type = "checkbox";
          headerRow.appendChild(checkbox);
          const th0 = document.createElement("th");
          th0.textContent = "ID";
          headerRow.appendChild(th0);
          const th1 = document.createElement("th");
          th1.textContent = "PREFIX";
          headerRow.appendChild(th1);
          const th2 = document.createElement("th");
          th2.textContent = "LETTER-CIDE";
          headerRow.appendChild(th2);
          const th3 = document.createElement("th");
          th3.textContent = "NAME";
          headerRow.appendChild(th3);
          const th4 = document.createElement("th");
          th4.textContent = "COUNTRY";
          headerRow.appendChild(th4);
          const th5 = document.createElement("th");
          th5.textContent = "MON";
          headerRow.appendChild(th5);
          const th6 = document.createElement("th");
          th6.textContent = "TUE";
          headerRow.appendChild(th6);
          const th7 = document.createElement("th");
          th7.textContent = "WED";
          headerRow.appendChild(th7);
          const th8 = document.createElement("th");
          th8.textContent = "THU";
          headerRow.appendChild(th8);
          const th9 = document.createElement("th");
          th9.textContent = "FRI";
          headerRow.appendChild(th9);
          const th10 = document.createElement("th");
          th10.textContent = "SAT";
          headerRow.appendChild(th10);
          const th11 = document.createElement("th");
          th11.textContent = "SUN";
          headerRow.appendChild(th11);
          thead.appendChild(headerRow);

          const tbody = document.createElement("tbody");
          data.forEach((data1) => {
            //曜日のID格納用
            let changeWeek = [];

            const row = document.createElement("tr");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.addEventListener("change", function (event) {
              if (event.target.checked) {
                console.log("チェックされました");
                checkedData = data1; //チェックされたデータを格納
                console.log(checkedData);
              } else {
                console.log("チェックが解除されました");
                checkedData = null;
              }
            });
            row.appendChild(checkbox);
            for (const key in data1) {
              const cell1 = document.createElement("td");
              const input = document.createElement("input");
              if (key === "id") {
                const cell = document.createElement("td");
                cell.textContent = data1[key];
                row.appendChild(cell);
              } else {
                input.type = "text";
                input.value = data1[key];
                input.readOnly = true;
                cell1.appendChild(input);
                row.appendChild(cell1);
              }
              if (
                key === "mon" ||
                key === "tue" ||
                key === "wed" ||
                key === "thu" ||
                key === "fri" ||
                key === "sat" ||
                key === "sun"
              ) {
                input.id = key + data1.id;
                changeWeek.push(input.id);
                console.log(changeWeek);
              }
            }
            const cell = document.createElement("td");
            const changeText = document.createElement("div");
            changeText.textContent = "CHANGE";
            changeText.addEventListener("click", () => {
              changeText.textContent = "UPDATE";
              changeAirplane(data1, changeText, changeWeek);
            });
            changeText.classList.add("changeBtn");
            cell.appendChild(changeText);
            row.appendChild(cell);
            tbody.appendChild(row);
          });
          // テーブルにヘッダーとボディを追加
          table.appendChild(thead);
          table.appendChild(tbody);

          // テーブルをHTMLに追加
          tableContainer.appendChild(table);
        });
      }
    });
  }

  // 編集
  function changeAirplane(data, cell, id) {
    console.log("click");
    for (let weekId of id) {
      let changeName = document.getElementById(weekId);
      changeName.readOnly = false;
      console.log(weekId);
    }
    cell.addEventListener("click", () => updateCategory(data, id));
  }



  //addボタンクリック時処理
  insertBtn.addEventListener("click", function () {
    console.log("click");
    const inputValues = [];
    const prefixNum = document.getElementById("input1").value;

    // 各input要素の値を取得し、inputValues配列に追加
    for (let i = 1; i <= 11; i++) {
      const input = document.getElementById(`input${i}`);
      inputValues.push(input.value.trim());
    }

    // チェック: nullまたは空欄が存在するか
    const hasNullEmpty = inputValues.some(
      (value) => value === null || value === ""
    );

    if (hasNullEmpty) {
      console.log("一つ以上の入力がnullまたは空欄です。");
      window.setTimeout(function () {
        alert("Something missing");
      }, 1000);
    }else if(isNaN(prefixNum)){
      console.log("prefixがint型ではないです");
      window.setTimeout(function () {
        alert("prefix must be number");
      }, 1000);
    } else {
      const insertData = {
        id: 0,
        prefix: prefixNum,
        letterCode: document.getElementById("input2").value,
        name: document.getElementById("input3").value,
        country: document.getElementById("input4").value,
        mon: document.getElementById("input5").value,
        tue: document.getElementById("input6").value,
        wed: document.getElementById("input7").value,
        thu: document.getElementById("input8").value,
        fri: document.getElementById("input9").value,
        sat: document.getElementById("input10").value,
        sun: document.getElementById("input11").value,
      };
      console.log(insertData);
      fetch("/insertAirplane", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(insertData),
      })
        .then((response) => {
          if (response.ok) {
            console.log("POST request processed");
            listAirplaneShow();
            // 画面推移後にポップアップを表示
            window.setTimeout(function () {
              alert("added successfully");
            }, 1000);
          } else {
            console.error("POST request failed");
            errarId.style.display = "block";
            // ポップアップを表示
            window.setTimeout(function () {
              alert("add unsuccessfull");
            }, 1000);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  });

  //deleteボタンクリック時処理
  deleteBtn.addEventListener("click", function () {
    console.log("click");
    const deleteData = {
      id: checkedData.id,
      prefix: checkedData.prefix,
      letterCode: checkedData.letterCode,
      name: checkedData.name,
      country: checkedData.country,
      mon: checkedData.mon,
      tue: checkedData.tue,
      wed: checkedData.wed,
      thu: checkedData.thu,
      fri: checkedData.fri,
      sat: checkedData.sat,
      sun: checkedData.sun,
    };
    console.log(deleteData);

    fetch("/airplaneDelete", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(deleteData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("DELETE request processed");
          listAirplaneShow();
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Deleted successfully");
          }, 1000);
        } else {
          console.error("DELETE request failed");
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Delete unsuccesfull");
          }, 1000);
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        // ポップアップを表示
        window.setTimeout(function () {
          alert("Delete unsuccesfull");
        }, 1000);
      });
  });

  //更新
  function updateCategory(data, id) {
    const newData = {
      id: data.id,
      prefix: data.prefix,
      letterCode: data.letterCode,
      name: data.name,
      country: data.country,
      mon: document.getElementById(id[0]).value,
      tue: document.getElementById(id[1]).value,
      wed: document.getElementById(id[2]).value,
      thu: document.getElementById(id[3]).value,
      fri: document.getElementById(id[4]).value,
      sat: document.getElementById(id[5]).value,
      sun: document.getElementById(id[6]).value,
    };

    console.log(newData);
    fetch("/airplaneUpdate", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("PUT request processed");
          listAirplaneShow();
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Updated successfully");
          }, 1000);
        } else {
          console.error("PUT request failed");
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Update unsuccessfull");
          }, 1000);
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        // ポップアップを表示
        window.setTimeout(function () {
          alert("Update unsuccessfull");
        }, 1000);
      });
  }

  listAirplaneShow();
});
