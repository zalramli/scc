<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h5 class="m-0 font-weight-bold text-primary">Data Bank Software</h5>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">Nama Peminta</th>
							<th class="text-center">Tanggal</th>
							<th class="text-center">Rating</th>
							<th class="text-center">Status</th>
							<th class="text-center">Aksi</th>
						</tr>
					</thead>
					<tbody>
						<?php
						$no = 1;
						foreach ($record as $data) :
						?>
							<tr>
								<td class="text-center"><?= $no++ ?></td>
								<td><?= $data->nama_eksternal ?></td>
								<td><?= date('d-m-Y',strtotime($data->tanggal_bs)) ?></td>
								<td><?= $data->rating ?></td>
								<td>
									<?php if ($data->status_bs == "Selesai") {
										echo '<span class="badge badge-success">Done</span>';
									} else {
										echo '<span class="badge badge-danger">unfinished</span>';
									}
									?>
								</td>
								<td>
									<a href="#" class="btn-sm btn-info btn_search" data-toggle="modal" data-target="#exampleModalCenter" data-idbs="<?= $data->kode_bank_s ?>">
										Detail</a>
									<a href="<?= base_url('admin/bank_software/delete/' . $data->kode_bank_s) ?>" class="btn btn-sm btn-danger tombol-hapus">Hapus</a>
									
								</td>
							</tr>
						<?php endforeach; ?>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="modal fade  bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">Detail Software</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table table-bordered table_1" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th class="text-center">No</th>
								<th class="text-center">Nama Software</th>
							</tr>
						</thead>
						<tbody id="daftar_barang">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<?= base_url(); ?>_assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$('.tombol-hapus').on('click', function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		Swal.fire({
			title: 'Apakah anda yakin?',
			text: "Data user akan dihapus",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Hapus'
		}).then((result) => {
			if (result.value) {
				document.location.href = href;
				// Swal.fire(
				// 	'Deleted!',
				// 	'Your file has been deleted.',
				// 	'success'
				// )
			}
		})
	});
</script>
<script>
	$('.btn_search').on('click', function() {
		var kode_bank_s = $(this).data("idbs");
		var table;
		table = $('.table_1').DataTable({
			"columnDefs": [{
				"targets": [0, 1],
				"className": "text-center"
			}],
			"bDestroy": true
		});
		table.clear();
		$.ajax({
			url: "<?php echo base_url() . 'admin/bank_software/detail_list_software'; ?>",
			method: "POST",
			data: {
				kode_bank_s: kode_bank_s
			},
			success: function(hasil) {
				var obj = JSON.parse(hasil);
				let data = obj['tbl_data'];
				if (data != '') {
					var no = 1;
					$.each(data, function(i, item) {
						var nama = data[i].nama;
						table.row.add([no, nama]);
						no = no + 1;
					});
				} else {
					$('.table_1').html('<h3>No data are available</h3>');
				}
				table.draw();
			}
		});
	});
</script>