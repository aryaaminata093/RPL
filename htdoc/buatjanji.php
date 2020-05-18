<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$idpasien = $_POST["idpasien"];
	$iddokter = $_POST["iddokter"];
	$harijanji = addslashes($_POST["harijanji"]);

	//$password = password_hash($password, PASSWORD_DEFAULT);

	$querycekdiri = "SELECT dp.psn_id, dp.psn_nama, dd.doc_id, dd.doc_nama, dd.doc_spesial,dj.harijanji FROM datapasien AS dp INNER JOIN datajanji AS dj ON dp.psn_id = dj.idpassien INNER JOIN datadokter AS dd ON dj.iddokter = dd.doc_id WHERE dp.psn_id='$idpasien' AND dj.harijanji='$harijanji';";
	$querycekdokter = "SELECT dp.psn_id, dp.psn_nama, dd.doc_id, dd.doc_nama, dd.doc_spesial,dj.harijanji FROM datapasien AS dp INNER JOIN datajanji AS dj ON dp.psn_id = dj.idpassien INNER JOIN datadokter AS dd ON dj.iddokter = dd.doc_id WHERE dd.doc_id='$iddokter' AND dj.harijanji='$harijanji';";
	$query = "INSERT INTO datajanji (idpassien, iddokter, harijanji) VALUES ('$idpasien', '$iddokter', '$harijanji');";

	$sqlcekdiri = mysqli_query($konek, $querycekdiri);
	$sqlcekdokter = mysqli_query($konek, $querycekdokter);

	if( mysqli_num_rows($sqlcekdiri) > 0 ){
		$result = array("success"=> 2,"message"=> "Anda sudah punya janji pada hari tersebut");
		echo json_encode($result);
	} else if(mysqli_num_rows($sqlcekdokter) > 0) {
		
		$result = array("success"=> 3,"message"=> "Dokter sudah punya janji pada hari tersebut");
		echo json_encode($result);
	} else {
		mysqli_query($konek, $query);
		$result = array("success"=> 1,"message"=> "berhasil" );
		echo json_encode($result);
	} 
}else{

	$result = array( "success"=> 0,"message"=> "error post");
	echo json_encode($result);
}
?>