import { Container } from "@mui/material"

export default function StockLayout({
  children, // will be a page or nested layout
}: {
  children: React.ReactNode
}) {
  return (
    <Container fixed sx={{ p: 2}}>
      {children}
    </Container>
  )
}
