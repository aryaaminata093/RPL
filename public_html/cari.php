<?php 

	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_POST['cari'];

		$queryada = "SELECT * FROM datadokter JOIN datajadwal ON doc_id=iddokter WHERE doc_spesial LIKE '%$cari%' OR doc_nama LIKE '%$cari%';";
		$query = "SELECT * FROM datadokter JOIN datajadwal ON doc_id=iddokter;";
		$data = array();
		
		$sql = mysqli_query($konek,$queryada);
		
	
	
	if( $sql = mysqli_query($konek, $queryada) ){
		while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
			"success"=> 1,
			"message" => "berhasil",
			"iddoc" => $row['doc_id'],
			"namadoc" => $row['doc_nama'],
			"spesialis" => $row['doc_spesial'],
			"idjadwal" => $row['idjadwal'],
			"jam" => $row['jam']
		));
	} 

		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
}
	?>