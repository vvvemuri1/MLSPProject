function generateLabs(style, name)
filename = fullfile(style, name);

% load onsets file
onsets = load(strcat(filename, '.onsets'));

% load notes file
notes_file = fopen(fullfile('notes', strcat(name, '.txt')));
notes = fgetl(notes_file);
while (~feof(notes_file))
    notes = [notes; {fgetl(notes_file)}];
end
fclose(notes_file);

[~, fs] = wavread(strcat(filename, '.wav'));

% convert sample to second
onsets = onsets./fs;
notes(1) = {'Ssil'};
% notes = [notes];

output_dir = fullfile('lab', style);

if(~exist(output_dir, 'dir'))
    mkdir(output_dir);
end

output_file = fopen(fullfile(output_dir, strcat(name, '.lab')), 'w');
for i = 1 : length(onsets)
    fprintf(output_file, '%f 125 %s\n', onsets(i), notes{i});
end
fclose(output_file);