import { Metadata } from 'next'
import { StockListTable } from '../components/stock'
 
export const metadata: Metadata = {
  title: 'Next.js',
}
 
export default function Page() {
  return <StockListTable/>
}