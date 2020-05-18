<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$id = $_POST["pasid"];

	//$password = password_hash($password, PASSWORD_DEFAULT);

	$query = "SELECT dp.psn_id, dp.psn_nama, dd.doc_id, dd.doc_nama, dd.doc_spesial,dj.harijanji FROM datapasien AS dp INNER JOIN datajanji AS dj ON dp.psn_id = dj.idpassien INNER JOIN datadokter AS dd ON dj.iddokter = dd.doc_id WHERE dp.psn_id='$id';";
	$sql = mysqli_query($konek, $query);

	if( mysqli_num_rows($sql) > 0 ){
		while ($row = mysqli_fetch_assoc($sql)){
				$result = array(
					"success" => 1,
					"message" => "berhasil",
					"id_pas" => $row['psn_id'],
					"namapasien" => $row['psn_nama'],
					"id_doc" => $row["doc_id"],
					"namadokter" => $row["doc_nama"],
					"harijanji" => $row["harijanji"],
					"spesialis" => $row["doc_spesial"],
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