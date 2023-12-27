import * as React from 'react';
import Box from '@mui/joy/Box';
import imageConfig from '../../config/config-banner.json'
import AspectRatio from '@mui/joy/AspectRatio';

export default function Banner() {

  const imageSource = imageConfig.localizations.find(i=>i.localization == 'CA')?.image_url

  return (

  <Box sx={{ height: 120, borderRadius: 'sm', p: 1, display:"block", marginTop: 6}}>
    <AspectRatio objectFit="cover" maxHeight={100}>
      <img
        src={imageSource}
        alt="A beautiful landscape."
      />
    </AspectRatio>
  </Box>
  );
}