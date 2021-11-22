export interface JournalEntry {
  username: string
  journalDate: Date
  orderIndex: number
  amount: number
  seq: number
  ndbNo: number
}
export interface JournalEntryResponse {
  jId: number
  username: string
  journalDate: Date
  orderIndex: number
  amount: number
  seq: number
  ndbNo: number
}
