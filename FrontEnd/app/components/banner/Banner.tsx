import * as React from 'react';
import Box from '@mui/joy/Box';
import imageConfig from '../../config/config-banner.json'
import AspectRatio from '@mui/joy/AspectRatio';

export default function Banner() {

  const imageSource = imageConfig.localizations.find(i=>i.localization == 'CA')?.image_url

  return (
    <AspectRatio objectFit="cover" maxHeight={100} >
      <img
        src={imageSource}
        alt="A beautiful landscape."
      />
    </AspectRatio>
  );
}