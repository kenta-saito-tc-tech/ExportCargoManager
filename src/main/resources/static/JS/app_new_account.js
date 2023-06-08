document.addEventListener("DOMContentLoaded", () => {
  //各ボタン
  const addAccount = document.getElementById("js-addAccount");

  //エラー
  const errarId = document.getElementById("js-errarId");
  const errarName = document.getElementById("js-errarName");
  const errarPass = document.getElementById("js-errarPass");
  const errarRes = document.getElementById("js-errarRes");
  //input
  const inputId = document.getElementById("js-inputId");
  const inputName = document.getElementById("js-inputName");
  const inputPass = document.getElementById("js-inputPass");
  const listSelect = document.getElementById("js-listSelect");

  //エラーメッセージ非表示
  errarId.style.display = "none";
  errarName.style.display = "none";
  errarPass.style.display = "none";
  errarRes.style.display = "none";

  //担当を入れる
  fetch("/responsibility").then((res) => {
    //RestControllerから受け取った値->res(成功/200 失敗/400)
    if (res.status === 400) {
      console.log("no");
    } else {
      res.json().then((data) => {
        data.forEach((dataEach) => {
          const option1 = document.createElement("option");
          option1.value = dataEach.id;
          option1.text = dataEach.name;
          listSelect.appendChild(option1);
        });
      });
    }
  });

  //addボタンクリック時処理
  addAccount.addEventListener("click", () => {
    const idText = inputId.value;
    const passText = inputPass.value;
    const nameText = inputName.value;
    const renonsibleText = listSelect.value;

    //判別用FLAG
    let flg = 0;

    if (idText == null || idText == "" || idText < 4) {
      errarId.style.display = "block";
      flg = 1;
    } else {
      errarId.style.display = "none";
    }
    if (passText == null || passText == "" || passText < 4) {
      errarPass.style.display = "block";
      flg = 1;
    } else {
      errarPass.style.display = "none";
    }
    if (nameText == null || nameText == "") {
      errarName.style.display = "block";
      flg = 1;
    } else {
      errarName.style.display = "none";
    }
    if (renonsibleText == null || renonsibleText == "") {
      errarRes.style.display = "block";
      flg = 1;
    } else {
      errarRes.style.display = "none";
    }

    if (flg === 0) {
      //データ格納用
      const data = {
        id: 0,
        loginId: idText,
        password: passText,
        name: nameText,
        responsibleId: renonsibleText,
      };

      console.log("click");
      console.log(data);

      fetch("/insertUser", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (response.ok) {
            console.log("POST request processed");
            window.location.href = "/login";
            window.setTimeout(function () {
              alert("add your account successfuly");
            }, 1000);
          } else {
            console.error("failed");
            // ポップアップを表示
            window.setTimeout(function () {
              alert("add your account failed");
            }, 1000);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  });
});
