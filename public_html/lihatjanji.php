<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$id = $_POST["pasid"];
	$tglantrian = $_POST["tglantrian"];

	//$password = password_hash($password, PASSWORD_DEFAULT);

	$query = "SELECT * FROM dataantrian JOIN datajadwal ON jadwalid=idjadwal JOIN datadokter ON iddokter=doc_id WHERE pasienid='$id' AND tglantrian='$tglantrian' AND statusantrian='0';";
	$sql = mysqli_query($konek, $query);

	if( mysqli_num_rows($sql) > 0 ){
		while ($row = mysqli_fetch_assoc($sql)){
				$result = array(
					"success" => 1,
					"message" => "berhasil",
					"id_doc" => $row["doc_id"],
					"namadokter" => $row["doc_nama"],
					"jam" => $row["jam"],
					"spesialis" => $row["doc_spesial"],
					"noantrian" => $row["noantrian"]
				);
		}

		echo json_encode($result);
		
	} else {
		
		$result = array("success"=> 404,"message"=> "tidak ada janji");
		echo json_encode($result);
	
	} 
	}else{

		$result = array( "success"=> 0,"message"=> "error post");
		echo json_encode($result);
	}
?>