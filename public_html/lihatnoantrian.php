<?php
include("koneksi.php");
if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$idpassien = $_POST['idpassien'];
	$tanggal = $_POST['tanggal'];

	$sql = "SELECT * FROM dataantrian WHERE tglantrian='$tanggal' AND pasienid='$idpassien' AND statusantrian='0'";
	$query = mysqli_query($konek, $sql);

	if( mysqli_num_rows($query) > 0 ) {
		while ($row = mysqli_fetch_assoc($query)){
			$result = array(
					"success" => 1,
					"message" => "berhasil",
					"noantrian" => $row['noantrian'],
					"jadwalid" => $row['jadwalid']
				);
		}
		
	} 
	else {
		$result = array("success"=> 2,"message"=> "kosong");
	}
	echo json_encode($result);


} 
?>
