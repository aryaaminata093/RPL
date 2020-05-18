<?php 

	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_POST['cari'];

		$queryada = "SELECT * FROM datadokter WHERE doc_hari LIKE '$cari' OR doc_spesial LIKE '%$cari%' OR doc_nama LIKE '%$cari%';";
		$query = "SELECT * FROM datadokter";
		$data = array();
		
		$sql = mysqli_query($konek,$queryada);
		
	}
	
	while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
			"success"=> 1,
			"message" => "berhasil",
			"iddoc" => $row['doc_id'],
			"namadoc" => $row['doc_nama'],
			"haridoc" => $row['doc_hari'],
			"spesialis" => $row['doc_spesial']
		));
	} 
	echo json_encode($data);
	?>