<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	require_once "koneksi.php";
	$id = $_POST["id"];
	$nama = $_POST["nama"];
	$tmptlhr = $_POST["tempatlahir"];
	$tgllhr = $_POST["tanggallahir"];
	$alamat = $_POST["alamat"];
	$jenkel = $_POST["jeniskelamin"];
	$goldar = $_POST["golongandarah"];


	$query = "UPDATE datapasien SET psn_nama = '$nama', tmpt_lahir = '$tmptlhr', tgl_lahir = '$tgllhr', alamat = '$alamat', psn_jk = '$jenkel', gol_darah = '$goldar'  WHERE psn_id = '$id';";
	$quer2 = "SELECT * FROM datapasien WHERE psn_id = '$id';";
	$sql = mysqli_query($konek, $query);
	$ambil = mysqli_query($konek, $quer2);
	if(mysqli_query($konek, $query)){
	 
	    while ($row = mysqli_fetch_assoc($ambil)){
		    $result = array(
			    "success" => 1,
		    	"message" => "berhasil",
		    	"id" => $row['psn_id'],
		    	"email" => $row['psn_email'],
		    	"nama" => $row["psn_nama"],
		    	"tmptlhr" => $row["tmpt_lahir"],
		    	"tgllhr" => $row["tgl_lahir"],
		    	"alamat" => $row["alamat"],
		    	"jk" => $row["psn_jk"],
		    	"goldar" => $row["gol_darah"]
			    );
    	}
    	echo json_encode($result);

    }else{

	    	$result = array( "success"=> 0,"message"=> "error update");
	    	echo json_encode($result);
	    }   
	}
?>