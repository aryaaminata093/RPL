<?php

	require_once "koneksi.php";
	$query = "SELECT * FROM datadokter";
	$data = array();
			
	$sql = mysqli_query($konek,$query);

	while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
			"iddoc" => $row['doc_id'],
			"namadoc" => $row['doc_nama'],
			"haridoc" => $row['doc_hari'],
			"spesialis" => $row['doc_spesial']
		));
	} 
	echo json_encode($data);
	?>