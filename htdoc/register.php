<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	$email = $_POST["email"];
	$password = $_POST["password"];
	$nama = $_POST["nama"];
	$tmplhr = null;
	$tgllhr = null;
	$alamat = null;
	$jk = null;
	$goldar = null;

	$password = password_hash($password, PASSWORD_DEFAULT);

	require_once "koneksi.php";

	$query = "INSERT INTO datapasien (psn_email, psn_nama, psn_password, tmpt_lahir, tgl_lahir, alamat, psn_jk, gol_darah) VALUES ('$email', '$nama', '$password', '$tmplhr', '$tgllhr', '$alamat', '$jk', '$goldar');";

	if( mysqli_query($konek, $query) ){
	
		$result = array("success"=> 1,"message"=> "berhasil" );

		echo json_encode($result);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
	} else{

		$result = array( "success"=> 0,"message"=> "error post");
		echo json_encode($result);
	}
?>