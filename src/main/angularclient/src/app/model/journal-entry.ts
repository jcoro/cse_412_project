export interface JournalEntry {
  username: string
  journal_date: Date
  order_index: number
  amount: number
  seq: number
  ndb_no: number
}
export interface JournalEntryResponse {
  j_id: number
  username: string
  journal_date: Date
  order_index: number
  amount: number
  seq: number
  ndb_no: number
  long_desc: string
  fdgrp_cd: number
  measurement: any[]
  nutrient_value: any[]
}
export interface JournalEntryUpdate {
  j_id: number
  username: string
  journal_date: Date
  order_index: number
  amount: number
  seq: number
  ndb_no: number
}
