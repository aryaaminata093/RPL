<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$idpasien = $_POST["idpasien"];
	$idjadwal = $_POST["idjadwal"];
	$tglantrian = $_POST["tglantrian"];

	$here = "SET @C := '0';";
	//$password = password_hash($password, PASSWORD_DEFAULT);
	
	$cekdiri = "SELECT * FROM dataantrian WHERE pasienid='$idpasien' AND tglantrian='$tglantrian';";

	$inser = "INSERT INTO dataantrian (tglantrian, pasienid, jadwalid) VALUES ('$tglantrian', '$idpasien', '$idjadwal');";
	$banyak = "SELECT @c := count(pasienid) FROM dataantrian WHERE jadwalid='$idjadwal' AND tglantrian='$tglantrian';";
	$query = "UPDATE dataantrian SET noantrian = IF(@c='0','1',@c) WHERE pasienid = '$idpasien' AND jadwalid='$idjadwal';";
	

	if(mysqli_num_rows(mysqli_query($konek,$cekdiri))<1){
	    mysqli_query($konek, $inser);
	    if(mysqli_query($konek,$here)){
	       mysqli_query($konek, $banyak);
	   	   mysqli_query($konek,$query);
		   $result = array( "success"=> 1,"message"=> "masuk");
		   echo json_encode($result);   
	    }else{
	        $result = array( "success"=> 0,"message"=> "error");
	    }
	} else {
		$result = array( "success"=> 2,"message"=> "sudah ada janji");
	echo json_encode($result);
	}

}else{


	$result = array( "success"=> 0,"message"=> "error post");
	echo json_encode($result);
	}
?>