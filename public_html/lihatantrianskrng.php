<?php
include("koneksi.php");
if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$idjadwal = $_POST['idjadwal'];
	$tanggal = $_POST['tanggal'];

	$sql = "SELECT * FROM dataantrian WHERE tglantrian='$tanggal' AND jadwalid='$idjadwal' AND statusantrian='0' ORDER BY noantrian DESC;";
	$query = mysqli_query($konek, $sql);

	if( mysqli_num_rows($query) > 0 ) {
		while ($row = mysqli_fetch_assoc($query)){
			$result = array(
					"success" => 1,
					"message" => "berhasil",
					"noantrian" => $row['noantrian'],
				);
		}
		
	} 
	else {
		$result = array("success"=> 2,"message"=> "kosong");
	}
	echo json_encode($result);


} 
?>
