document.addEventListener("DOMContentLoaded", () => {
  const imgae3D = document.getElementById("js-image3D");

  //3D初期画面の初期表示を少し遅らせ、値を取れるようにする
  window.setTimeout(function () {
    const inputHeight = document.getElementById("js-inputHeight").value;
    const inputWidth = document.getElementById("js-inputWidth").value;
    const inputDepth = document.getElementById("js-inputDepth").value;

    cargoShowFirst(inputHeight, inputWidth, inputDepth);
  }, 100);

  /*
ボタンが押された時の画面更新
*/
  const js3dButton = document.getElementById("js-3dButton");
  js3dButton.addEventListener("click", () => {
    const newInputHeight = document.getElementById("js-inputHeight").value;
    const newInputWidth = document.getElementById("js-inputWidth").value;
    const newInputDepth = document.getElementById("js-inputDepth").value;

    cargoShowFirst(newInputHeight, newInputWidth, newInputDepth);
  });

  /*
初期画面
*/
  function cargoShowFirst(heightNum, widthNum, depthNum) {

    imgae3D.innerHTML = "";

    /* scene(シーン)の作成 */
    var scene = new THREE.Scene();

    /* camera(カメラ)の作成 */
    var camera = new THREE.PerspectiveCamera(
      75,
      window.innerWidth / window.innerHeight,
      0.1,
      1000
    );

    /* renderer(レンダラー)の作成 */
    var renderer = new THREE.WebGLRenderer();
    var canvasWidth = window.innerWidth / 2.5; // 幅を半分に縮小
    var canvasHeight = window.innerHeight / 2.5; // 高さを半分に縮小
    renderer.setSize(canvasWidth, canvasHeight);
    document.body.appendChild(renderer.domElement);

    // object(オブジェクト)の作成
    var width = widthNum;
    var height = heightNum;
    var depth = depthNum;
    var geometry = new THREE.BoxGeometry(width, height, depth);
    var material = new THREE.MeshBasicMaterial({ color: 0x00ff00 });
    var cube = new THREE.Mesh(geometry, material);
    scene.add(cube);

    // 長方形の各角に線を作成
    var lineMaterial = new THREE.LineBasicMaterial({ color: 0x000000 });

    var lineGeometry1 = new THREE.Geometry();
    lineGeometry1.vertices.push(
      new THREE.Vector3(-width / 2, -height / 2, -depth / 2),
      new THREE.Vector3(-width / 2, -height / 2, depth / 2)
    );
    var line1 = new THREE.Line(lineGeometry1, lineMaterial);
    cube.add(line1);

    var lineGeometry2 = new THREE.Geometry();
    lineGeometry2.vertices.push(
      new THREE.Vector3(-width / 2, height / 2, -depth / 2),
      new THREE.Vector3(-width / 2, height / 2, depth / 2)
    );
    var line2 = new THREE.Line(lineGeometry2, lineMaterial);
    cube.add(line2);

    var lineGeometry3 = new THREE.Geometry();
    lineGeometry3.vertices.push(
      new THREE.Vector3(width / 2, height / 2, -depth / 2),
      new THREE.Vector3(width / 2, height / 2, depth / 2)
    );
    var line3 = new THREE.Line(lineGeometry3, lineMaterial);
    cube.add(line3);

    var lineGeometry4 = new THREE.Geometry();
    lineGeometry4.vertices.push(
      new THREE.Vector3(width / 2, -height / 2, -depth / 2),
      new THREE.Vector3(width / 2, -height / 2, depth / 2)
    );
    var line4 = new THREE.Line(lineGeometry4, lineMaterial);
    cube.add(line4);

    var lineGeometry5 = new THREE.Geometry();
    lineGeometry5.vertices.push(
      new THREE.Vector3(-width / 2, -height / 2, depth / 2),
      new THREE.Vector3(-width / 2, height / 2, depth / 2),
      new THREE.Vector3(width / 2, height / 2, depth / 2),
      new THREE.Vector3(width / 2, -height / 2, depth / 2),
      new THREE.Vector3(-width / 2, -height / 2, depth / 2) // 最初の頂点に戻る
    );
    var line5 = new THREE.Line(lineGeometry5, lineMaterial);
    cube.add(line5);

    var lineGeometry6 = new THREE.Geometry();
    lineGeometry6.vertices.push(
      new THREE.Vector3(-width / 2, -height / 2, -depth / 2),
      new THREE.Vector3(-width / 2, height / 2, -depth / 2),
      new THREE.Vector3(width / 2, height / 2, -depth / 2),
      new THREE.Vector3(width / 2, -height / 2, -depth / 2),
      new THREE.Vector3(-width / 2, -height / 2, -depth / 2) // 最初の頂点に戻る
    );
    var line6 = new THREE.Line(lineGeometry6, lineMaterial);
    cube.add(line6);

    /* camera(カメラ)の位置設定 */
    camera.position.set(0, 0, 600);
    camera.lookAt(new THREE.Vector3(0, 0, 0));
    var axes = new THREE.AxesHelper(1000);

    /* 繰り返しの処理 */
    var animate = function () {
      requestAnimationFrame(animate);

      cube.rotation.x += 0.01;
      cube.rotation.y += 0.01;

      renderer.render(scene, camera);
    };

    animate();

    imgae3D.appendChild(renderer.domElement);
  }
});
