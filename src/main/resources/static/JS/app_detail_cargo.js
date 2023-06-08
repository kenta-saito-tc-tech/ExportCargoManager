document.addEventListener("DOMContentLoaded", () => {
  const yourResponsibleId = document.getElementById("js-responsibleId").value;

  //テキスト情報
  const listSelect = document.getElementById("js-listSelect");
  const listLithium = document.getElementById("js-listLithium");
  const listReserve = document.getElementById("js-listReserve");

  const inputName = document.getElementById("js-inputName");
  const inputCloseDate = document.getElementById("js-inputCloseDate");
  const inputArrivalDate = document.getElementById("js-inputArrivalDate");
  const inputHeight = document.getElementById("js-inputHeight");
  const inputWidth = document.getElementById("js-inputWidth");
  const inputDepth = document.getElementById("js-inputDepth");
  const inputWeight = document.getElementById("js-inputWeight");
  const inputDescription = document.getElementById("js-inputDescription");

  //ボタン
  const updateBtn = document.getElementById("js-updateBtn");
  const deletetBtn = document.getElementById("js-deletetBtn");

  //エラー
  const errarName = document.getElementById("js-errarName");
  const errarRes = document.getElementById("js-errarRes");
  const errarCloseDate = document.getElementById("js-errarCloseDate");
  const errarArrivalDate = document.getElementById("js-errarArrivalDate");
  const errarLithium = document.getElementById("js-errarLithium");
  const errarReserve = document.getElementById("js-errarReserve");

  //エラーメッセージ非表示
  errarName.style.display = "none";
  errarRes.style.display = "none";
  errarCloseDate.style.display = "none";
  errarArrivalDate.style.display = "none";
  errarLithium.style.display = "none";
  errarReserve.style.display = "none";

  //IDの値
  const id = document.getElementById("js-id").value;
  console.log(id);

  //DESTINATIONを入れる
  fetch(`/destination?searchId=${yourResponsibleId}`).then((res) => {
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

  //初期表示
  function cargoDetailShow() {
    fetch(`/cargo?searchId=${id}`).then((res) => {
      //RestControllerから受け取った値->res(成功/200 失敗/400)
      if (res.status === 400) {
        console.log(res);
      } else {
        res.json().then((data) => {
          console.log(data);
          inputName.value = data.name;
          listSelect.value = data.destinationId;
          inputCloseDate.value = data.closeDate;
          inputArrivalDate.value = data.arrivalDate;
          listLithium.value = data.lithium;
          inputHeight.value = data.height;
          inputWidth.value = data.width;
          inputDepth.value = data.depth;
          inputWeight.value = data.weight;
          inputDescription.value = data.description;
          listReserve.value = data.reserveStatus;
        });
      }
    });
  }

  //updateボタンクリック時処理
  updateBtn.addEventListener("click", () => {
    console.log("click");

    const nameText = inputName.value;
    const destText = listSelect.value;
    const closeText = inputCloseDate.value;
    const arrivalText = inputArrivalDate.value;
    const lithiumText = listLithium.value;
    const heightText = inputHeight.value;
    const widthText = inputWidth.value;
    const depthText = inputDepth.value;
    const weightText = inputWeight.value;
    const descText = inputDescription.value;
    const reserveText = listReserve.value;

    //判別用FLAG
    let flg = 0;

    if (nameText == null || nameText == "") {
      errarName.style.display = "block";
      flg = 1;
    } else {
      errarName.style.display = "none";
    }

    var pattern = /^\d{4}-\d{2}-\d{2}$/; // 正規表現パターン
    if (pattern.test(closeText)) {
      errarCloseDate.style.display = "none";
    } else {
      errarCloseDate.style.display = "block";
      flg = 1;
    }

    if (pattern.test(arrivalText)) {
      errarArrivalDate.style.display = "none";
    } else {
      errarArrivalDate.style.display = "block";
      flg = 1;
    }

    if (
      isNaN(heightText) ||
      isNaN(widthText) ||
      isNaN(depthText) ||
      isNaN(weightText)
    ) {
      flg = 1;

      window.setTimeout(function () {
        alert("Between height to weight must be number");
      }, 1000);
    }

    if (flg === 0) {
      //データ格納用
      const data = {
        id: id,
        name: nameText,
        destinationId: destText,
        closeDate: closeText,
        arrivalDate: arrivalText,
        lithium: lithiumText,
        height: heightText,
        width: widthText,
        depth: depthText,
        weight: weightText,
        description: descText,
        reserveStatus: reserveText,
        createdAt: "",
        updatedAt: "",
      };
      console.log(data);

      fetch("/updateCargo", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (response.ok) {
            console.log("POST request processed");
            window.location.href = "/main-page";
            // 画面推移後にポップアップを表示
            window.setTimeout(function () {
              alert("Updated successfully");
            }, 1000);
          } else {
            console.error("POST request failed");
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
  });

  //deleteボタンクリック時処理
  deletetBtn.addEventListener("click", () => {
    const nameText = inputName.value;
    const destText = listSelect.value;
    const closeText = inputCloseDate.value;
    const arrivalText = inputArrivalDate.value;
    const lithiumText = listLithium.value;
    const heightText = inputHeight.value;
    const widthText = inputWidth.value;
    const depthText = inputDepth.value;
    const weightText = inputWeight.value;
    const descText = inputDescription.value;
    const reserveText = listReserve.value;

    //データ格納用
    const deleteData = {
      id: id,
      name: nameText,
      destinationId: destText,
      closeDate: closeText,
      arrivalDate: arrivalText,
      lithium: lithiumText,
      height: heightText,
      width: widthText,
      depth: depthText,
      weight: weightText,
      description: descText,
      reserveStatus: reserveText,
      createdAt: "",
      updatedAt: "",
    };

    fetch("/deleteCargo", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(deleteData),
    })
      .then((response) => {
        if (response.ok) {
          console.log("DELETE request processed");
          window.location.href = "/main-page";
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Deleted successfully");
          }, 1000);
        } else {
          console.error("DELETE request failed");
          // ポップアップを表示
          window.setTimeout(function () {
            alert("Deleted unsuccesfull");
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

  cargoDetailShow();
});
