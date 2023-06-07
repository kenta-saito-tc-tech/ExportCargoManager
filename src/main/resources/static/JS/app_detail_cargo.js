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
  const insertBtn = document.getElementById("js-insertBtn");

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

  function cargoDetailShow() {
    //初期表示
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

  cargoDetailShow();

});
