import { Box } from "@mui/material"

export default function StockLayout({
  children, // will be a page or nested layout
}: {
  children: React.ReactNode
}) {
  return (
    <Box component="section"  sx={{ p: 2}}>
      {children}
    </Box>
  )
}
