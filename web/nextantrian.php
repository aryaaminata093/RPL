<?php
include("config.php");
if(isset($_POST['next'])){

	$idjadwal = $_POST['idjadwal'];
	$tanggal = $_POST['tanggal'];

	$sql = "DELETE FROM rumahsakit.antrian
			WHERE id_jadwal='$idjadwal' AND tglantrian='$tanggal'
			ORDER BY noantrian
			LIMIT 1";
	$query = mysqli_query($konek, $sql);

	if( $query==TRUE ) {
		echo "Next antrian berhasil! :)";
		header('Location: antrian.php');
		
	} 
	else {
		echo "Next antrian gagal! :( \r\n";
		die ('SQL Error: ' . mysqli_error($konek));
	}


} else {
	die("Akses dilarang...");
}
?>
