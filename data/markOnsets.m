function markOnsets(style, name)
filename = strcat(style, '/', name);

onsets_file = fopen(strcat(filename, '.wav.out'));
onsets = fscanf(onsets_file, '%d', [1 inf]);
fclose(onsets_file);

wav = wavread(strcat(filename, '.wav'));

figure; hold on;
plot(wav, 'blue');
plot(onsets, 'red');

[x ~] = ginput;

output_file = fopen(strcat(filename, '.onsets'), 'w');
fprintf(output_file, '%d\n', x);
fclose(output_file);

end
