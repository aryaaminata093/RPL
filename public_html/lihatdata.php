<?php
	
	$query = "SELECT * FROM datapasien";
	$data = array();
	require_once "koneksi.php";

	if( $sql = mysqli_query($konek, $query) ){
		while ($row = mysqli_fetch_array($sql)){
			array_push($data, array(
				"id" => $row['psn_id'],
				"email" => $row['psn_email'],
				"password" => $row["psn_password"],
				"nama" => $row["psn_nama"],
				"tmptlhr" => $row["tmpt_lahir"],
				"tgllhr" => $row["tgl_lahir"],
				"alamat" => $row["alamat"],
				"jk" => $row["psn_jk"],
				"goldar" => $row["gol_darah"]
			));
		}


		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
?>