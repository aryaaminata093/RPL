<?php
include("config.php");
if(isset($_POST['next'])){

	$idjadwal = $_POST['idjadwal'];
	$tanggal = $_POST['tanggal'];

	$sql = "UPDATE rumahsakit.antrian
			SET statusantrian = '1'
			WHERE id_jadwal='$idjadwal' AND tglantrian='$tanggal' 
			AND noantrian=(SELECT min(noantrian) FROM rumahsakit.antrian WHERE statusantrian='0')";
	$query = mysqli_query($konek, $sql);

	if( $query==TRUE ) {
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


