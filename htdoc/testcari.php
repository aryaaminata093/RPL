<?php 
include 'koneksi.php';
?>
 
<h3>Form Pencarian</h3>
 
<form action="testcari.php" method="post">
	<label>Cari :</label>
	<input type="text" name="cari">
	<input type="submit" value="Cari">
</form>
 
<?php 
if(isset($_POST['cari'])){
	$cari = $_POST['cari'];
	echo "<b>Hasil pencarian : ".$cari."</b>";
}
?>
 
<table border="1">
	<tr>
		<th>No</th>
		<th>Nama</th>
	</tr>
	<?php 

	require_once "koneksi.php";
	$queryada = "SELECT * FROM datadokter WHERE doc_hari LIKE '$cari' OR doc_spesial LIKE '%$cari%' OR doc_nama LIKE '%$cari%';";
	//$query2 = "SELECT * FROM datadokter WHERE doc_spesial LIKE '%$cari%';";
	//$query3 = "SELECT * FROM datadokter WHERE doc_nama LIKE '%$cari%';";
	$query = "SELECT * FROM datadokter";

	if(isset($_POST['cari'])){
		$cari = $_POST['cari'];
		$data = mysqli_query($konek,$queryada);
		
	}else{
		$data = mysqli_query($konek,$query);		
	}
	$no = 1;
	while($d = mysqli_fetch_array($data)){
	?>
	<tr>
		<td><?php echo $no++; ?></td>
		<td><?php echo $d['doc_nama']; ?></td>
	</tr>
	<?php } ?>
</table>