function generateLabs(style, name)
filename = fullfile(style, name);

onsets = load(strcat(filename, '.onsets'));
notes_file = fopen(fullfile('notes', strcat(name, '.txt')));
notes = fscanf(notes_file, '%s');
fclose(notes_file);

notes(1) = [];

[~, fs] = wavread(strcat(filename, '.wav'));

% convert sample to second
onsets = onsets./fs;

lab = [onsets; 125; notes];

output_dir = fullfile('lab', style);
if(~exist(output_dir, 'dir'))
    mkdir(output_dir);
end
output_file = fopen(fullfile(output_dir, strcat(filename, '.lab')), 'w');
fprintf(output_file, '%d %d %s\n', lab);
fclose(output_file);