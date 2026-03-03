import { Input, OnChanges, SimpleChanges, AfterViewInit, Component, ViewChild, OnInit, inject } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';

export interface SongDetails {
  author: string;
  title: string;
  playedHour: string;
  playedDate: string;
}

@Component({
  selector: 'app-songs-table',
  standalone: true,
  styleUrls: ['./songsTable.component.scss'],
  templateUrl: './songsTable.component.html',
  imports: [MatTableModule, MatPaginatorModule],
})

export class SongsTable implements OnInit, AfterViewInit, OnChanges {

  @Input() selectedRadio!: string;

  http = inject(HttpClient);

  dataSource = new MatTableDataSource<SongDetails>([]);
  displayedColumns: string[] = ['author', 'title', 'playedHour', 'playedDate'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    this.loadRadio(this.selectedRadio);
  }
  
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedRadio'] && this.selectedRadio) {
      this.loadRadio(this.selectedRadio);
    }
  }

  loadRadio(radioName: string): void {
  this.http
    .get<Record<string, SongDetails[]>>(
      `https://6a7e1443-459c-4f6c-8d38-e86df3ba8d4a.mock.pstmn.io/api/${radioName}`
    )
    .subscribe((result) => {
      const songsArray = Object.values(result)[0];
      this.dataSource.data = songsArray;
    });
}
}
